package cn.think.in.java.learing.concurrent.block.queue;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {

  static PriorityBlockingQueue priorityBlockingQueue;

  public static void main(String[] args) throws InterruptedException {
    priorityBlockingQueue = new PriorityBlockingQueue();
    priorityBlockingQueue.put(new LinkedBlockingDequeDemo());
    System.out.println(priorityBlockingQueue.take());

  }

}
