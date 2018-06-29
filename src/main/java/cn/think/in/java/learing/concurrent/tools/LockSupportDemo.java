package cn.think.in.java.learing.concurrent.tools;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

  static Thread c ;

  public static void main(String[] args) {

    new Thread(()->{
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("1");
      LockSupport.unpark(c);
      System.out.println("2");
    }).start();

    c = Thread.currentThread();

    LockSupport.park(c);
    LockSupport.unpark(c);
    System.out.println("3");

  }

}
