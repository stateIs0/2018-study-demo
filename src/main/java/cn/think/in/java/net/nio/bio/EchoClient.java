package cn.think.in.java.net.nio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient {

  public static void main(String[] args) {
    Socket client = null;
    PrintWriter writer = null;
    BufferedReader bb = null;

    client = new Socket();
    try {
      client.connect(new InetSocketAddress("localhost", 8000));
      writer = new PrintWriter(client.getOutputStream(), true);
      writer.println("Hello");
      writer.flush();

      bb = new BufferedReader(new InputStreamReader(client.getInputStream()));
      System.out.println("from server : " + bb.readLine());

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      writer.close();
      if (client != null) {
        try {
          client.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      try {
        bb.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
