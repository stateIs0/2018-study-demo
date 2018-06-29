package cn.think.in.java.learing.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockText implements Runnable {

  /**
   * Re - entrant - Lock
   * 重入锁，表示在单个线程内，这个锁可以反复进入，也就是说，一个线程可以连续两次获得同一把锁。
   * 如果你不允许重入，将导致死锁。注意，lock 和 unlock 次数一定要相同，如果不同，就会导致死锁和监视器异常。
   *
   * synchronized 只有2种情况：1继续执行，2保持等待。
   */
  static ReentrantLock lock = new ReentrantLock();
  int i;

  public LockText(int i) {
    this.i = i;
  }

  public static void main(String[] args) throws InterruptedException {
    LockText lockText1 = new LockText(1);
    LockText lockText2 = new LockText(2);
    Thread t1 = new Thread(lockText1, "1");
    Thread t2 = new Thread(lockText2, "2");
    t1.start();
    t2.start();
//    t1.join();
//    t2.join();
  }

  @Override
  public void run() {
//    for (int j = 0; j < 1000000; j++) {
//      lock.lock();
//      try {
//        Thread.sleep(100);
//        i++;
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      } finally {
//        // 因为lock 如果发生了异常，是不会释放锁的，所以必须在 finally 块中释放锁
//        // synchronized 发生异常会主动释放锁
//        lock.unlock();
//        lock.unlock();
//      }
//    }
    if (i == 1) {
      a();
    } else {
      b();
    }
  }

  void a() {
    lock.lock();
    try {
      System.out.println("a");
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  void b() {
    System.out.println("阻塞");
    lock.lock();
    try {
      System.out.println("b");
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}


class A {

  static Lock lock = new ReentrantLock();
  static Condition condition = lock.newCondition();


  static void a() {
    lock.lock();
    try {
      condition.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("in");
    lock.unlock();
  }

  public static void main(String[] args) {
    new Thread(() ->
        a()
    ).start();
    a();

  }
}
