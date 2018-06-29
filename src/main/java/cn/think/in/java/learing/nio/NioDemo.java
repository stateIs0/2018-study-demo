package cn.think.in.java.learing.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * NIO 三个关键的组件：
 *  1. Channel 通道：对应着一个Socket。在 Channel 的众多实现中，有一个 SelectableChannel 实现，表示可被选择的通道。
 *                  任何一个 SelectableChannel 都可以将自己注册到 Selector 中。 这样，这个 Channel 就被 Selector 管理。
 *                  而一个 Selector 可以管理多个 SelectableChannel。
 *
 *  2. Buffer 缓存：数据需要包装成 Buffer 才能和 Channel交互。
 *
 *  3. Select 选择器：管理着 Channel。轮询 Channel。时刻处于等待状态。
 *
 */
public class NioDemo {

  private Selector selector;
  private ExecutorService tp = Executors.newCachedThreadPool();

  public static Map<Socket, Long> time_stat = new HashMap<>(10240);

  public void startServer() throws IOException {

    // 通过工厂方法获得一个 Selector 对象的实例
    selector = SelectorProvider.provider().openSelector();
    // 获得 Channel 实例
    ServerSocketChannel ssc = SelectorProvider.provider().openServerSocketChannel();
    // 设置非阻塞，当然也可以是阻塞的。这样才可以向 Channel 注册感兴趣的时间。
    ssc.configureBlocking(false);
    // 端口绑定
    InetSocketAddress isa = new InetSocketAddress("localhost", 8000);
    ssc.socket().bind(isa);
    // 注册感兴趣的事件，并且在数据准备好时，得到必要的通知。这里的事件是 accept，当 selector 发现 channel 有新的客户端连接时
    // 就会通知 channel 处理。
    SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
    acceptKey.interestOps();

    // 该循环主要任务就是等待-分发网络消息
    for (; ; ) {
      // 该方法是阻塞方法，如果当前没有任何数据准备好，他就会等待。一旦有数据可读，他就会返回。他的返回值是已经准备就绪
      // 的 selectionKey 的数量。
      int kesy = selector.select();
      System.out.println(kesy);
      Set readyKeys = selector.selectedKeys();
      Iterator i = readyKeys.iterator();
      long e;
      // 挨个处理所有的 Channel 数据
      while (i.hasNext()) {
        // 得到 SelectionKey 实例
        SelectionKey sk = (SelectionKey) i.next();
        // 将这个元素移除，否则就会重复处理相同的 SelectionKey。
        i.remove();
        // 如果是 acceptable 状态，
        if (sk.isAcceptable()) {
          doAccept(sk);
          // 判断 Channel 是否已经可以读了
        } else if (sk.isValid() && sk.isReadable()) {
          if (!time_stat.containsKey(((SocketChannel) sk.channel()).socket())) {
            time_stat.put(((SocketChannel) sk.channel()).socket(), System.currentTimeMillis());
          }
          doRead(sk);
        // 判断Channel 是否准备好进行写。
        } else if (sk.isValid() && sk.isWritable()) {
          doWrite(sk);
          e = System.currentTimeMillis();
          long b = time_stat.remove(((SocketChannel) sk.channel()).socket());
          // 输出处理这个 Socket 连接的耗时
          System.out.println("spend: " + (e - b) + "ms");
        }
      }
    }

  }

  private void doAccept(SelectionKey sk) {
    // 得到 key 对应的通道
    ServerSocketChannel server = (ServerSocketChannel) sk.channel();
    SocketChannel clientChannel;

    try {
      // 和普通 socket 一样，当有一个新的客户端连接接入时，就会有一个新的Channel 产生代表这个连接。
      // 生成的 clientChannel 就表示和客户端通信的通道
      clientChannel = server.accept();
      // 这个Channel 配置为非阻塞模式，也就是要求系统在准备好IO后，再通知我们的线程来读取或者写入。
      clientChannel.configureBlocking(false);
      // 将这个通道注册到 selector，兴趣事件为 read。
      SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
      // 创建一个线程
      EchoClient echoClient = new EchoClient();
      // 将这个线程作为附件附加到这个连接的 selectionKey 上，这样再整个连接的处理过程中，我们都可以共享整个 echoClient 实例。
      // attachment 方法可以拿到这个对象
      clientKey.attach(echoClient);

      InetAddress clientAddress = clientChannel.socket().getInetAddress();
      System.out.println("Accepted connection from " + clientAddress.getHostAddress() + "." + "");


    } catch (IOException e) {
      System.out.println("Failed to accept new client");
      System.out.println();
      e.printStackTrace();
    }


  }


  private void doRead(SelectionKey sk) throws IOException {
    SocketChannel channel = (SocketChannel) sk.channel();
    ByteBuffer bb = ByteBuffer.allocate(8192);
    int len;

    try {
      len = channel.read(bb);
      if (len < 0) {
        disconnect(sk);
        return;
      }
    } catch (IOException e) {
      System.out.println("Failed to read from client");
      e.printStackTrace();
      disconnect(sk);
      return;
    }

    bb.flip();
    tp.execute(new HandleMsg(sk, bb));
  }

  private void doWrite(SelectionKey sk) throws IOException {
    SocketChannel channel = (SocketChannel) sk.channel();
    // 同一个 SelectorKey 共享同一个attach
    EchoClient echoClient = (EchoClient) sk.attachment();
    LinkedList outq = echoClient.getOutputQueue();
    // 弹出内容
    ByteBuffer bb = (ByteBuffer) outq.getLast();

    try {
      // 写回客户端
      int len = channel.write(bb);
      if (len == -1) {
        disconnect(sk);
        return;
      }

      if (bb.remaining() == 0) {
        // 发送完成后，移除缓存对象
        outq.removeLast();
      }

    } catch (IOException e) {
      System.out.println("Failed to write to client");
      e.printStackTrace();
      disconnect(sk);
    }

    if (outq.size() == 0) {
      // 当队列中没有数据，就将事件改成读，否则，每次Channel 准备好写事件时，都会来执行 doWrite 方法，
      // 而你又无数据可写，显然不合理，所以，这个操作很重要
      sk.interestOps(SelectionKey.OP_READ);
    }

  }


  private void disconnect(SelectionKey sk) throws IOException {
    sk.channel().close();
  }



  class HandleMsg implements Runnable {

    SelectionKey sk;
    ByteBuffer bb;

    public HandleMsg(SelectionKey sk, ByteBuffer bb) {
      this.sk = sk;
      this.bb = bb;
    }

    @Override
    public void run() {
      EchoClient echoClient = (EchoClient) sk.attachment();
      // 将消息压入队列
      echoClient.enqueue(bb);
      // 重新注册感兴趣的写事件，这样再通道准备好写入时，就能通知线程
      sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

      // 强迫 selector 立即返回
      selector.wakeup();
    }
  }

  /**
   * 参加运算的两个数据，按二进制位进行“与”运算。

     运算规则：0&0=0;   0&1=0;    1&0=0;     1&1=1;

     即：两位同时为“1”，结果才为“1”，否则为0
   */
  public static void main(String[] args) {
  }

}
