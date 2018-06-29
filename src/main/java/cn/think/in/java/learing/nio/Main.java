package cn.think.in.java.learing.nio;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    NioDemo nioDemo = new NioDemo();
    nioDemo.startServer();
  }

}
