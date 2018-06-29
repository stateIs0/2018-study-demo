package cn.think.in.java.learing.concurrent.countController;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一个控制多线程并发调用 RPC 的流控程序。
 * 使用 Semaphore 控制访问量，
 */
public class CountControllerTest {

  static Semaphore semaphore = new Semaphore(100);

  public static void main(String[] args) {

    Executor timeTask = Executors.newScheduledThreadPool(1);
    ((ScheduledExecutorService) timeTask).scheduleAtFixedRate(
        () -> semaphore.release(100 - semaphore.availablePermits()), 1000, 1000,
        TimeUnit.MILLISECONDS);

    Executor pool = Executors.newFixedThreadPool(100);

    for (int i = 0; i < 100; i++) {
      final int num = i;
      pool.execute(() -> {
        for (; ; ) {
          for (int j = 0; j < 200; j++) {
            if (semaphore.tryAcquire()) {
              callRpc(num, j);
            } else {
              System.err.println("call fail");
            }
          }
        }
      });
    }
  }

  private static void callRpc(int num, int j) {
    System.out.println(String.format("%s - %s: %d %d", new Date(), Thread.currentThread(), num, j));

  }

}

class CountControllerTest2 {

  static AtomicInteger count = new AtomicInteger();

  public static void main(String[] args) {
    Executor timeTask = Executors.newScheduledThreadPool(1);
    ((ScheduledExecutorService) timeTask).scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        count.getAndSet(100);
      }
    }, 1000, 1000, TimeUnit.MILLISECONDS);

    Executor pool = Executors.newFixedThreadPool(100);

    for (int i = 0; i < 100; i++) {
      final int num = i;
      pool.execute(() -> {
        for (; ; ) {
          for (int j = 0; j < 200; j++) {
            if (count.get() >= 0) {// 快影响速判断，否则大量的 CAS 操作将会定时任务更新计数器 count
              if (count.decrementAndGet() >= 0) {
                callRpc(num, j);
              }
            }
          }
        }
      });
    }
  }

  private static void callRpc(int num, int j) {
    System.out.println(String.format("%s - %s: %d %d", new Date(), Thread.currentThread(), num, j));

  }

}

