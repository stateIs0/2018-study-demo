package cn.think.in.java.learing.concurrent.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo2 {

  private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

  public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

    new Thread(() -> {
      System.out.println("thread");
      try {
        Thread.sleep(1000);
        cyclicBarrier.await();
        System.out.println("thread over");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
    ).start();

    cyclicBarrier.await();
    System.out.println("main");
  }

}
