package cn.think.in.java.learing.nio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * 模拟网络不好的 客户端
 */
public class HeavySocketClient {

  private static ExecutorService ep = Executors.newCachedThreadPool();
  private static final int sleep_time = 1000 * 1000 * 1000;

  public static class EchoClient implements Runnable {

    @Override
    public void run() {
      Socket client = null;
      PrintWriter writer = null;
      BufferedReader reader = null;

      try {
        client = new Socket();
        client.connect(new InetSocketAddress("localhost", 8000));
        writer = new PrintWriter(client.getOutputStream(), true);
        writer.print("H");
        LockSupport.parkNanos(sleep_time);
        writer.print("e");
        LockSupport.parkNanos(sleep_time);
        writer.print("l");
        LockSupport.parkNanos(sleep_time);
        writer.print("l");
        LockSupport.parkNanos(sleep_time);
        writer.print("o");
        LockSupport.parkNanos(sleep_time);
        writer.print("!");
        LockSupport.parkNanos(sleep_time);
        writer.print("" + LocalDateTime.now());
//        writer.println();
        writer.flush();

        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        System.out.println("from server : " + reader.readLine());

      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        writer.close();
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        try {
          client.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }
  }

  public static void main(String[] args) {
    EchoClient ec = new EchoClient();
    for (int i = 0; i < 10; i++) {
      ep.execute(ec);

    }
  }

}
