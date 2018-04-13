package cn.think.in.java.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class LingDuDemo {

  public static void main(String[] args) throws IOException {
    int port = 8000;
// 通过open()方法找到Selector
    Selector selector = Selector.open();
// 打开服务器的通道
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
// 服务器配置为非阻塞
    serverSocketChannel.configureBlocking(false);
    ServerSocket serverSocket = serverSocketChannel.socket();
    InetSocketAddress address = new InetSocketAddress(port);
// 进行服务的绑定
    serverSocket.bind(address);
// 注册到selector，等待连接
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    System.out.println("服务器运行，端口：" + 8000);

// 数据缓冲区
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    while (true) {
      if ((selector.select()) > 0) { // 选择一组键，并且相应的通道已经准备就绪
        System.out.println("-----");
        Set<SelectionKey> selectedKeys = selector.selectedKeys();// 取出全部生成的key
        Iterator<SelectionKey> iter = selectedKeys.iterator();
        while (iter.hasNext()) {
          SelectionKey key = iter.next(); // 取出每一个key
          if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            // 接收新连接 和BIO写法类是都是accept
            SocketChannel client = server.accept();
            // 配置为非阻塞
            client.configureBlocking(false);
            byteBuffer.clear();
            // 向缓冲区中设置内容
            byteBuffer.put(("当前的时间为：" + new Date()).getBytes());
            byteBuffer.flip();
            // 输出内容
            client.write(byteBuffer);
            client.register(selector, SelectionKey.OP_READ);
          } else if (key.isReadable() && key.isValid()) {
            SocketChannel client = (SocketChannel) key.channel();
            byteBuffer.clear();
            // 读取内容到缓冲区中
            int readSize = client.read(byteBuffer);
            if (readSize > 0) {
              System.out.println("服务器端接受客户端数据:" + new String(byteBuffer.array(), 0, readSize));
              client.register(selector, SelectionKey.OP_WRITE);
            }
          } else if (key.isWritable() && key.isValid()) {
            SocketChannel client = (SocketChannel) key.channel();
            byteBuffer.clear();
            // 向缓冲区中设置内容
            byteBuffer.put(("欢迎关注匠心零度，已经收到您的反馈，会第一时间回复您。感谢支持！！！").getBytes());
            byteBuffer.flip();
            // 输出内容
            client.write(byteBuffer);
            client.register(selector, SelectionKey.OP_READ);
          }
        }
        selectedKeys.clear(); // 清楚全部的key
      }
    }

  }

}
