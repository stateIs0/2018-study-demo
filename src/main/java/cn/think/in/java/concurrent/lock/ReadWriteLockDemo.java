package cn.think.in.java.concurrent.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

  static Lock lock = new ReentrantLock();
  static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

  static Lock readLock = reentrantReadWriteLock.readLock();
  static Lock writeLock = reentrantReadWriteLock.writeLock();

  int value;

  public Object handleRead(Lock lock) throws InterruptedException {
    try {
      lock.lock();
      // 模拟读操作，读操作的耗时越多，读写锁的优势就越明显
      Thread.sleep(1000);
      return value;
    } finally {
      lock.unlock();
    }
  }

  public void handleWrite(Lock lock, int index) throws InterruptedException {
    try {
      lock.lock();
      Thread.sleep(1000); // 模拟写操作
      value = index;

    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    final ReadWriteLockDemo demo = new ReadWriteLockDemo();
    Runnable readRunnable = new Runnable() {
      @Override
      public void run() {
        try {
          demo.handleRead(readLock);
//          demo.handleRead(lock);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    Runnable writeRunnable = new Runnable() {
      @Override
      public void run() {
        try {
          demo.handleWrite(writeLock, new Random().nextInt());
//          demo.handleWrite(lock, new Random().nextInt());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    /**
     * 使用读写锁，这段程序只需要2秒左右
     * 使用普通的锁，这段程序需要20秒左右。
     */

    for (int i = 0; i < 18; i++) {
      new Thread(readRunnable).start();
    }

    for (int i = 18; i < 20; i++) {
      new Thread(writeRunnable).start();
    }


  }

}
