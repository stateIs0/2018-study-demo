package cn.think.in.java.learing.concurrent.block.queue;

import java.util.concurrent.LinkedTransferQueue;

public class LinkedTransferQueueDemo {

  static LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue();

  public static void main(String[] args) throws InterruptedException {
    linkedTransferQueue.put("hello");
    System.out.println(linkedTransferQueue.take());

    System.out.println( (1 << 7) >>> 1);
  }



}
