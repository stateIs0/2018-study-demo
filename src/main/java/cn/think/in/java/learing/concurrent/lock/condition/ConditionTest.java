package cn.think.in.java.learing.concurrent.lock.condition;

import java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁的好搭档
 *
 * await 使当前线程等待，同时释放当前锁，当其他线程中使用 signal 或者 signalAll 方法时，线程会重新获得锁并继续执行。
 *       或者当线程被中断时，也能跳出等待，这和 Object.wait 方法很相似。
 * awaitUninterruptibly() 方法与 await 方法基本相同，但是它并不会在等待过程中响应中断。
 * singal（） 该方法用于唤醒一个在等待中的线程，相对的 singalAll 方法会唤醒所有在等待的线程，这和 Object.notify 方法很类似。
 */
public class ConditionTest implements Runnable {

  static ReentrantLock lock = new ReentrantLock();

  static ConditionObject condition = (ConditionObject) lock.newCondition();


  @Override
  public void run() {

    try {
//
      lock.lock();
      // 该线程会释放 lock 的锁，也就是说，一个线程想调用 condition 的方法，必须先获取 lock 的锁。
      // 否则就会像 object 的 wait 方法一样，监视器异常
      condition.await();
//      condition.awaitUninterruptibly();
      System.out.println("Thread is going on");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ConditionTest t = new ConditionTest();
    Thread t1 = new Thread(t);
    t1.start();
    Thread.sleep(1000);
    // 通知 t1 继续执行
    // main 线程必须获取 lock 的锁，才能调用 condition 的方法。否则就是监视器异常，这点和 object 的 wait 方法是一样的。
    lock.lock(); // IllegalMonitorStateException
    // 从 condition 的等待队列中，唤醒一个线程。
    condition.signal();
    condition.signalAll();
    lock.unlock();
  }
}

class Test {

  public static void main(String[] args) {

    ReentrantLock lock = new ReentrantLock();

    ConditionObject condition = (ConditionObject) lock.newCondition();

    lock.lock();

    lock.unlock();
  }
}
