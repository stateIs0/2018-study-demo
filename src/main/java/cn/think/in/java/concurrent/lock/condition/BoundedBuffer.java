package cn.think.in.java.concurrent.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {

  final Lock lock = new ReentrantLock();
  final Condition notFull = lock.newCondition();
  final Condition notEmpty = lock.newCondition();

  final Object[] items = new Object[100];
  int putptr, takeptr, count;

  public void put(Object x) throws InterruptedException {
    lock.lock();
    try {
      while (count == items.length) {
        notFull.await();
      }
      items[putptr] = x;
      if (++putptr == items.length) {
        putptr = 0;
      }
      ++count;
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  public Object take() throws InterruptedException {
    lock.lock();
    try {
      while (count == 0) {
        notEmpty.await();
      }
      Object x = items[takeptr];
      if (++takeptr == items.length) {
        takeptr = 0;
      }
      --count;
      notFull.signal();
      return x;
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    BoundedBuffer boundedBuffer = new BoundedBuffer();

    new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 101; i++) {
          try {
            boundedBuffer.put(i);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

      }
    }).start();


    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          while (true) {
            System.out.println(boundedBuffer.take());
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }).start();


  }
}
