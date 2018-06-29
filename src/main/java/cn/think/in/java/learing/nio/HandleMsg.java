package cn.think.in.java.learing.nio;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

public class HandleMsg implements Runnable {


  final int a;

  SelectionKey sk;
  ByteBuffer bb;

  public HandleMsg(int a, SelectionKey sk, ByteBuffer bb) {
    this.a = a;
    this.sk = sk;
    this.bb = bb;
  }

  @Override
  public void run() {
    EchoClient echoClient = (EchoClient) sk.attachment();
    echoClient.enqueue(bb);

    sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);


  }

}
