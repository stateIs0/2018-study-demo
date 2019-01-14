package cn.think.in.java.learing.concurrent.lock.interrupted;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 响应中断：Thread.sleep()、 Thread.join() 或 Object.wait()
 * ？？？
 *
 * 1. Lock 在等待锁的过程中，如何响应中断？lockInterruptibly 借助 LockSupport
 * 2 Condition 在等待的过程中，如何响应中断？await 借助 LockSupport
 * 3. synchronized 阻塞的时候如何响应中断？不响应
 * 4. Object.wait 如何响应中断？会响应中断
 * 5. LockSupport park 是否响应中断？响应中断但不抛出异常,即：立即返回。
 */
public class InterruptedDemo {

  static Lock lock = new ReentrantLock();
  static Condition condition = lock.newCondition();

  public static void main(String[] args) throws InterruptedException {
    Task task = new Task();
    Thread t1 = new Thread(task);
    Thread t2 = new Thread(task);

    t1.start();
    Thread.sleep(100);
    t2.start();

    Thread.sleep(1000);

    t2.interrupt();

  }

  public static void lock() throws InterruptedException {
    System.out.println("lock out");
    lock.lock();
    try {
      System.out.println("own lock");
      System.out.println("interrupt status : " + Thread.interrupted());

      condition.await();

    } finally {
      lock.unlock();
      System.out.println("interrupt status : " + Thread.interrupted());
    }
  }

  static class Task implements Runnable {

    @Override
    public void run() {
      try {
        synchronizedTest();
      } catch (InterruptedException e) {
        System.out.println("interrupt status run: " + Thread.currentThread().isInterrupted());
      }
    }

    private void synchronizedTest() throws InterruptedException {
      System.out.println("synchronized out");
      synchronized (this) {
        System.out.println("synchronized inner");
        System.out.println("interrupt status synchronizedTest: " + Thread.currentThread().isInterrupted());
        wait();
      }
    }


    private void lockSupportTest() throws InterruptedException {
      System.out.println("interrupt status 1: " + Thread.currentThread().isInterrupted());
      LockSupport.park(this);
      System.out.println("hello");
      System.out.println("interrupt status 2: " + Thread.currentThread().isInterrupted());
      throw new InterruptedException();
    }
  }

}


