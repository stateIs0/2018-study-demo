package cn.think.in.java.concurrent.container;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {

  public static void main(String[] args) throws InterruptedException {
    int i = 0;
    ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
    for (; ; ) {
      queue.add(""+ i);
      queue.poll();
      i++;
    }


  }

  static void a() throws InterruptedException {
    int i = 0;
    System.out.println("restart");
    restartFromHead:
    for (; ; ) {
      System.out.println("outoutoutoutoutoutoutoutoutoutoutoutoutoutout");
      for (; ; ) {
        System.out.println("in");
        Thread.sleep(1000);
        i++;
        if (i % 2 == 0) {
          continue restartFromHead;
        }
      }
    }
  }

}

class TestLeak {

  public static void main(String[] args) {
    ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();
    queue.add(new Object()); //Required for the leak to appear.
    Object object = new Object();
    int loops = 0;
    Runtime rt = Runtime.getRuntime();
    long last = System.currentTimeMillis();
    while (true) {
      if (loops % 10000 == 0) {
        long now = System.currentTimeMillis();
        long duration = now - last;
        last = now;
        System.err.printf("duration=%d q.size=%d memory max=%d free=%d total=%d%n", duration,
            queue.size(), rt.maxMemory(), rt.freeMemory(), rt.totalMemory());
      }
      queue.add(object);
      queue.remove(object);
      ++loops;
    }
  }
}
