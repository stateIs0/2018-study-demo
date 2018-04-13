package cn.think.in.java.net.nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class EchoServer {

  public static final int PORT = 8000;
  private AsynchronousServerSocketChannel server;

  public EchoServer() throws IOException {
    server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT));
  }

  public void start() {
    System.out.println("Server listen on " + PORT);
    // 注册事件和事件完成后的处理器
    // attachment 是一个附件，作用是让当前线程和后续的回调方法可以共享信息，他会在后续调用中，传递给 Handler。
    // accept 做了两件事情。1：发起accept请求，告诉系统可以开始监听端口了。2：注册 completion 实例，成功则调用completed 方法，失败调用 failed 方法。
    server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
      final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

      // 异步操作成功时调用。说明客户端已经成功连接。
      public void completed(AsynchronousSocketChannel result, Object attachment) {
        System.out.println(Thread.currentThread().getName());
        Future<Integer> writeResult = null;
        try {
          byteBuffer.clear();
          // read 方法时异步的。这里调用get 是为了方便，其实可以做完其他事情再get
          result.read(byteBuffer).get(100, TimeUnit.SECONDS);
          byteBuffer.flip();
          // write 方法也是异步的。
          writeResult = result.write(byteBuffer);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        } catch (TimeoutException e) {
          e.printStackTrace();
        } finally {
          try {
            // 服务器开始准备下一个客户端的连接
            server.accept(null, this);
            // 但是确保之前的 write 操作已经完成，get 方法进行等待
            writeResult.get();
            // 关闭客户端
            result.close();
          } catch (Exception e) {
            System.out.println(e.toString());
          }
        }
      }
      // 异步操作失败时调用
      public void failed(Throwable exc, Object attachment) {
        System.out.println("failed : " + exc);
      }
    });
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    new EchoServer().start();
    // 主线程可以继续自己的行为
    while (true) {
      // 如果没有死循环行为，start 运行后，程序就退出了
      Thread.sleep(1000);
    }

  }

}
