package cn.think.in.java.tools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 相当于join功能，让调用  await 方法的线程等待 countdownlatch 的线程执行完毕
 */
public class CountDownLatchTest implements Runnable {

  /**
   * 表示需要10个线程完成任务，等待在倒计时上的线程才能继续运行。
   */
  static final CountDownLatch end = new CountDownLatch(10);
  static final CountDownLatchTest test = new CountDownLatchTest();

  @Override
  public void run() {
    try {
      // 模拟检查任务
      Thread.sleep(new Random().nextInt(10) * 1000);
      System.out.println("check complete");
      end.countDown();
      System.out.println("check end");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService exec = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10; i++) {
      exec.submit(test);
    }

    end.await();

    System.out.println("Fire");

    exec.shutdown();
  }
}

class AA {

  AA prev;
  AA next;

  void a() {
    prev = null;
  }


  AA getA() {
    return prev;
  }


  public static void main(String[] args) {
    AA aa = new AA();
    aa.prev = new AA();
    aa.next = new AA();
    AA a1 = aa.getA();
    aa.a();
    System.out.println(a1);
    a1.next = null;


  }
}
