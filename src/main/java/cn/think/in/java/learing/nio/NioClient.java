package cn.think.in.java.learing.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class NioClient {

  private Selector selector;

  public void init(String ip, int port) throws IOException {
    selector = SelectorProvider.provider().openSelector();


    SocketChannel channel1 = SocketChannel.open();
    channel1.configureBlocking(false);
    channel1.connect(new InetSocketAddress(ip, port));
    channel1.register(selector, SelectionKey.OP_CONNECT);

    SocketChannel channel2 = SocketChannel.open();
    channel2.configureBlocking(false);
    channel2.connect(new InetSocketAddress(ip, port));
    channel2.register(selector, SelectionKey.OP_CONNECT);
  }

  public void working() throws IOException {
    while (true) {
      if (!selector.isOpen()) {
        break;
      }
      selector.select();
      Iterator iterator = selector.selectedKeys().iterator();
      while (iterator.hasNext()) {
        SelectionKey selectionKey = (SelectionKey) iterator.next();
        iterator.remove();
        // 连接事件发生
        if (selectionKey.isConnectable()) {
          connect(selectionKey);
        } else if (selectionKey.isReadable()) {
          read(selectionKey);
        }
      }

    }
  }

  private void read(SelectionKey selectionKey) throws IOException {
    SocketChannel channel = (SocketChannel) selectionKey.channel();
    ByteBuffer byteBuffer = ByteBuffer.allocate(100);
    channel.read(byteBuffer);
    byte[] data = byteBuffer.array();
    String msg = new String(data).trim();
    System.out.println("客户端收到信息：" + msg);
    channel.close();
    selectionKey.selector().close();
  }

  private void connect(SelectionKey selectionKey) throws IOException {
    SocketChannel channel = (SocketChannel) selectionKey.channel();
    // 如果正在连接，则完成连接
    if (channel.isConnectionPending()) {
      channel.finishConnect();
    }
    channel.configureBlocking(false);
    channel.write(ByteBuffer.wrap(new String("hello server! \r \n").getBytes()));
    // 写完之后注册读事件为感兴趣的事件
    channel.register(selector, SelectionKey.OP_READ);
  }

  public static void main(String[] args) throws IOException {
    NioClient client = new NioClient();
    client.init("localhost", 8000);
    client.working();

  }


}
