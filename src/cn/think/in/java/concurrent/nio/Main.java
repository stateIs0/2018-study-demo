package cn.think.in.java.concurrent.nio;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    NioDemo nioDemo = new NioDemo();
    nioDemo.startServer();
  }

}
