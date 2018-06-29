package cn.think.in.java.learing.nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Arrays;

public class AioClient {


  public static void m1ain(String[] args) throws IOException, InterruptedException {
    final AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
    client.connect(new InetSocketAddress("localhost", 8000), null,
        new CompletionHandler<Void, Object>() {
          public void completed(Void result, Object attachment) {
            client.write(ByteBuffer.wrap("Hello!".getBytes()), null,
                new CompletionHandler<Integer, Object>() {
                  @Override
                  public void completed(Integer result, Object attachment) {
                    try {
                      ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                      client.read(byteBuffer, byteBuffer,
                          new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                              byteBuffer.flip();
                              System.out.println(new String(byteBuffer.array()));
                              try {
                                client.close();
                              } catch (Exception e) {
                                e.printStackTrace();
                              }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {

                            }
                          });
                    } catch (Exception e) {
                      e.printStackTrace();
                    }
                  }

                  @Override
                  public void failed(Throwable exc, Object attachment) {

                  }
                });
          }

          public void failed(Throwable exc, Object attachment) {

          }
        });
    // 由于主线程马上结束，这里等待上述处理全部完成
    Thread.sleep(1000);
  }

  public static void main(String[] args) {

    int[] a = new int[10];
    for (int i = 0; i < 10; i++) {
      a[i] = i;
    }

    int[] b = new int[20];
    int[] newArray = Arrays.copyOf(a, 20);
    Arrays.fill(newArray, a.length, newArray.length, 1000);
    System.out.println(Arrays.toString(newArray));
  }



}
