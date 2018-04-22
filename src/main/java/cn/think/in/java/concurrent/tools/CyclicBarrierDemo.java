package cn.think.in.java.concurrent.tools;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 *
 * 比 CountDownLatch 非常类似，但是更复杂，更强大
 *
 * 阻止线程继续执行，要求线程在栅栏出等待。
 *
 * cyclic 表示循环，表示这个计数器可以反复使用。而CountDownLatch无法进行重复使用。
 */
public class CyclicBarrierDemo {

  static class Soldier implements Runnable {

    // 军人
    String soldier;
    // 循环栅栏
    final CyclicBarrier cyclic;

    public Soldier(CyclicBarrier cyclic, String soldier) {
      this.cyclic = cyclic;
      this.soldier = soldier;
    }

    @Override
    public void run() {
      // 等待所有士兵到齐
      try {
        System.out.println("准备");
        /*
        * 内部维护一个数字，梅个
        * */
        cyclic.await();// 到了 10 才开始走，否则线程等待

        doWork();
        // 等待所有士兵完成工作
        cyclic.await();
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      }

    }

    private void doWork() {
      try {
        Thread.sleep(Math.abs(new Random().nextInt() % 10000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(soldier + ": 任务完成");
    }
  }

  /**
   * 当计数器一次计数完成后，系统会派一个线程执行的这个线程的run方法。
   */
  static class BarrierRun implements Runnable {

    boolean flag;
    int N;

    public BarrierRun(boolean flag, int n) {
      this.flag = flag;
      N = n;
    }

    @Override
    public void run() {
      if (flag) {
        System.out.println("司令：【士兵 " + N + "个， 任务完成！】");
      } else {
        System.out.println("司令：【士兵 " + N + "个， 集合完毕！】");
        flag = true;
      }
    }
  }


  public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
    final int n = 10;

    Thread[] allSoldier = new Thread[n];
    boolean flag = false;
    // parties 表示计数总数，也就是参与的线程总数， barrierAction 就是当计数器一次计数完成后，系统会执行的动作
    CyclicBarrier cyclic = new CyclicBarrier(n, new BarrierRun(false, n));

    // 设置屏障点，主要是为了执行这个方法
    System.out.println("集合队伍");
    for (int i = 0; i < n; i++) {
      System.out.println("士兵" + i + "报道");
      allSoldier[i] = new Thread(new Soldier(cyclic, "士兵" + i));
      allSoldier[i].start();
      if (i== 5){ // 会导致所有的线程全部停止 BrokenBarrierException * 9 + InterruptedException * 1
        allSoldier[i].interrupt();

      }
    }

    cyclic.await();

    System.out.println(cyclic.getParties());
    System.out.println(cyclic.getNumberWaiting());
    System.out.println(cyclic.isBroken());
  }

  static CyclicBarrierDemo demo = new CyclicBarrierDemo();

  public static void mai2n(String[] args) {
    CyclicBarrierDemo in = demo;
    changeDemo();
    System.out.println(in);
    System.out.println(demo);
    System.out.println(in == demo);
  }

  private static void changeDemo() {
    demo = new CyclicBarrierDemo();
  }

}
