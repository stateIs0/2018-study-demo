package cn.think.in.java.concurrent.nio;

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

    selector = SelectorProvider.provider().openSelector();
    ServerSocketChannel ssc = ServerSocketChannel.open();
    ssc.configureBlocking(false);

    InetSocketAddress isa = new InetSocketAddress("localhost", 8000);
//    InetSocketAddress isa = new InetSocketAddress(8000);
    ssc.socket().bind(isa);

    SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

    for (; ; ) {
      selector.select();
      Set readyKeys = selector.selectedKeys();
      Iterator i = readyKeys.iterator();
      long e = 0;
      while (i.hasNext()) {
        SelectionKey sk = (SelectionKey) i.next();
        i.remove();

        if (sk.isAcceptable()) {
          doAccept(sk);
        } else if (sk.isValid() && sk.isReadable()) {
          if (!time_stat.containsKey(((SocketChannel) sk.channel()).socket())) {
            time_stat.put(((SocketChannel) sk.channel()).socket(), System.currentTimeMillis());
          }
          doRead(sk);
        } else if (sk.isValid() && sk.isWritable()) {
          doWrite(sk);
          e = System.currentTimeMillis();
          long b = time_stat.remove(((SocketChannel) sk.channel()).socket());
          System.out.println("spend: " + (e - b) + "ms");
        }
      }
    }

  }

  private void doWrite(SelectionKey sk) {
    SocketChannel channel = (SocketChannel) sk.channel();
    EchoClient echoClient = (EchoClient) sk.attachment();
    LinkedList outq = echoClient.getOutputQueue();

    ByteBuffer bb = (ByteBuffer) outq.getLast();

    try {
      int len = channel.write(bb);
      if (len == -1) {
        disconnect(sk);
        return;
      }

      if (bb.remaining() == 0) {
        outq.removeLast();
      }

    } catch (IOException e) {
      System.out.println("Failed to write to client");
      e.printStackTrace();
      disconnect(sk);
    }

    if (outq.size() == 0) {
      sk.interestOps(SelectionKey.OP_READ);
    }

  }

  private void doRead(SelectionKey sk) {
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

  private void disconnect(SelectionKey sk) {
  }

  private void doAccept(SelectionKey sk) {
    ServerSocketChannel server = (ServerSocketChannel) sk.channel();
    SocketChannel clientChannel;

    try {
      clientChannel = server.accept();
      clientChannel.configureBlocking(false);

      SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);

      EchoClient echoClient = new EchoClient();
      clientKey.attach(echoClient);

      InetAddress clientAddress = clientChannel.socket().getInetAddress();
      System.out.println("Accepted connection from " + clientAddress.getHostAddress() + "." + "");


    } catch (IOException e) {
      System.out.println("Failed to accept new client");
      System.out.println();
      e.printStackTrace();
    }


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
      echoClient.enqueue(bb);

      sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

      // 强迫 selector 立即返回
      selector.wakeup();
    }
  }


}
