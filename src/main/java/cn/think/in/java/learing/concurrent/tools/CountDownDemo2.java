package cn.think.in.java.learing.concurrent.tools;

import java.util.concurrent.CountDownLatch;

public class CountDownDemo2 {

  private static CountDownLatch c = new CountDownLatch(1);

  public static void main(String[] args) throws InterruptedException {
    new Thread(() -> {
      System.out.println("1---");
      System.out.println(c.getCount());
      c.countDown();
      System.out.println(c.getCount());
      c.countDown();
      System.out.println("over");
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(c.getCount());
    }).start();

    c.await();
    Thread.sleep(2000);
    System.out.println("main");
  }

}
