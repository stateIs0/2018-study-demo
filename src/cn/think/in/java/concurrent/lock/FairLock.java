package cn.think.in.java.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable {

  // 公平锁和非公平锁的结果完全不同
  /*
  * 10 获得锁
    10 获得锁
    10 获得锁
    10 获得锁
    10 获得锁
    10 获得锁
    10 获得锁
    10 获得锁
    10 获得锁
    10 获得锁
    9 获得锁
    9 获得锁
    9 获得锁
    9 获得锁
    9 获得锁
    9 获得锁
    9 获得锁
    9 获得锁
    9 获得锁
    9 获得锁
    ======================下面是公平锁，上面是非公平锁
    10 获得锁
    9 获得锁
    10 获得锁
    9 获得锁
    10 获得锁
    9 获得锁
    10 获得锁
    9 获得锁
    10 获得锁
    9 获得锁
    10 获得锁
    9 获得锁
    10 获得锁
    9 获得锁
    10 获得锁
    9 获得锁
    10 获得锁
    9 获得锁
    10 获得锁
    9 获得锁
    10 获得
  *
  * */
  static ReentrantLock unFairLock = new ReentrantLock(false);
  static ReentrantLock fairLock = new ReentrantLock(true);

  @Override
  public void run() {
    while (true) {
      try {
        fairLock.lock();
        System.out.println(Thread.currentThread().getId() + " 获得锁");
      } finally {
        fairLock.unlock();
      }
    }
  }

  /**
   * 默认是不公平的锁，设置为 true 为公平锁
   *
   * 公平：在多个线程的争用下，这些锁倾向于将访问权授予等待时间最长的线程；
   * 使用公平锁的程序在许多线程访问时表现为很低的总体吞吐量（即速度很慢，常常极其慢）
   * 还要注意的是，未定时的 tryLock 方法并没有使用公平设置
   *
   * 不公平：此锁将无法保证任何特定访问顺序，但是效率很高
   *
   */
  public static void main(String[] args) {
    FairLock fairLock = new FairLock();
    Thread t1 = new Thread(fairLock, "cxs - t1");
    Thread t2 = new Thread(fairLock, "cxs - t2");
    t1.start();
    t2.start();
  }
}
