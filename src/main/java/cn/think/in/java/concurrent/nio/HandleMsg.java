package cn.think.in.java.concurrent.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

public class HandleMsg implements Runnable {

  SelectionKey sk;
  ByteBuffer bb;

  public HandleMsg(SelectionKey sk, ByteBuffer bb) {
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
