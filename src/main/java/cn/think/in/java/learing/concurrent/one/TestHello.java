package cn.think.in.java.learing.concurrent.one;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestHello implements Runnable {

  private final CyclicBarrier cyclicBarrier;

  public TestHello(CyclicBarrier cyclicBarrier) {
    this.cyclicBarrier = cyclicBarrier;
  }

  Lock lock = new ReentrantLock();
  Condition condition = lock.newCondition();


  @Override
  public void run() {
//    synchronized (this) {
//      System.out.println("wait out");
//      try {
//        wait();
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }

    try {
      Thread.sleep(1000000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("hello");

  }
}

class Main1 {


  public static void main(String[] args) throws InterruptedException {

    int count = 2;

    CyclicBarrier cyclicBarrier = new CyclicBarrier(count);

    Executor executor = Executors.newFixedThreadPool(3);
    ((ExecutorService) executor).shutdownNow();
    for (int i = 0; i < count; i++) {
      executor.execute(new TestHello(cyclicBarrier));

      while ((!((ExecutorService) executor).isTerminated())) {
        System.out.println("sleep");
        Thread.sleep(1000);
      }
    }
  }

}