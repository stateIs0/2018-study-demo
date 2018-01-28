package cn.think.in.java.concurrent.one;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {


  static Lock lock1 = new ReentrantLock();
  static Lock lock2 = new ReentrantLock();

  public static void main(String[] args) {

    new Thread(() -> {
      new DeadLock().resource1();
    }
    ,"1").start();

    new Thread(() -> {
      new DeadLock().resource2();
    }
    , "2").start();

    new Thread(() -> System.out.println("hello")).start();
  }

  void resource1() {

//    synchronized ("resource1") {
    lock1.lock();
    System.out.println("获取资源1");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // 等待
    resource2();
    lock1.unlock();
//    }

  }

  void resource2() {
//    synchronized ("resource1") {
    lock2.lock();
    System.out.println("获取资源2");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    resource1();
    lock2.unlock();
//    }
  }
}

class Afdf {

  String a;

  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    System.out.println(sb.hashCode());
  }


}
