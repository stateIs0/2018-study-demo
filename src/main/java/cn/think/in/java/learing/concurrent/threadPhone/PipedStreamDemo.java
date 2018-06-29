package cn.think.in.java.learing.concurrent.threadPhone;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedStreamDemo {

  public static void main(String[] args) throws IOException {
    PipedWriter writer = new PipedWriter();
    PipedReader reader = new PipedReader();
    PipedInputStream inputStream = new PipedInputStream();
    PipedOutputStream outputStream = new PipedOutputStream();

    // 将输出流和输入流连接
    writer.connect(reader);
    Thread printThread = new Thread(new Print(reader));

    printThread.start();

    int receive;

    try {
      while ((receive = System.in.read()) != -1) {
        // 从main线程写到 print 线程
        writer.write(receive);
      }
    } finally {
      writer.close();
    }


  }

  static class Print implements Runnable {

    private PipedReader in;

    public Print(PipedReader in) {
      this.in = in;
    }

    @Override
    public void run() {
      int receive;

      try {
        while ((receive = in.read()) != -1) {
          // 读取 main 线程发送过来的数据并打印
          System.out.print((char) receive);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


}
