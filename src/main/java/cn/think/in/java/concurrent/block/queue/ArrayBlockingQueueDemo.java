package cn.think.in.java.concurrent.block.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDemo {

  static ArrayBlockingQueue arrayBlockingQueue  = new ArrayBlockingQueue(1);

  public static void main(String[] args) throws InterruptedException {
    arrayBlockingQueue.put(1);
    System.out.println(arrayBlockingQueue.take());
  }



}
