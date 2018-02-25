package cn.think.in.java.concurrent.nio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务器
 */
public class MultiThreadEchoServer {

  private static ExecutorService service = Executors.newCachedThreadPool();

  static class HandleMsg implements Runnable {

    Socket clientSocket;

    public HandleMsg(Socket clientSocket) {
      this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
      BufferedReader is = null;
      PrintWriter os = null;
      try {
        is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        os = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine = null;
        long b = System.currentTimeMillis();
        while ((inputLine = is.readLine()) != null) {
          os.println(inputLine);
        }

        long e = System.currentTimeMillis();
        // 打印处理时间
        System.out.println("spend : " + (e - b) + "ms");

      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          if (is != null) {
            is.close();
          }
          if (os != null) {
            os.close();
          }
          clientSocket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    ServerSocket echoServer = null;
    try {
      echoServer = new ServerSocket(8000);
      while (true) {
        Socket clientSocket = echoServer.accept();
        System.out.println(clientSocket.getRemoteSocketAddress() + " connect !");
        service.execute(new HandleMsg(clientSocket));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
