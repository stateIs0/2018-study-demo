package cn.think.in.java.pool;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import sun.misc.Unsafe;

/**
 * {@linkplain ThreadPoolExecutor}
 * {@linkplain ScheduledThreadPoolExecutor}
 */
public class ThreadPoolDemo {

  /*
  * 该方法返回一个固定线程数量的线程池。该线程池中的线程数量始终不变。当有一个新的任务提交时，线程池中若有空闲线程，
  * 则立即执行，若没有，则新的任务会被暂存在一个任务队列中，待有线程空闲时，便处理在任务队列中的任务。
  * */
  ExecutorService service1 = Executors.newFixedThreadPool(1);// 固定线程池/
  /*
  * 该方法返回一个可根据实际情况调整线程数量的线程池，线程池的线程数量不确定，但若有空闲线程可以复用，则会优先使用可复用
  * 的线程，所有线程均在工作，如果有新的任务提交，则会创建新的线程处理任务。所有线程在当前任务执行完毕后，将返回线程池进行复用。
  * */
  ExecutorService service2 = Executors.newCachedThreadPool();// 缓存线程池
  /*
  * 该方法返回一个只有一个线程的线程池。若多余一个任务被提交到该线程池，任务会被保存在一个任务队列中，待线程空闲，按
  * 先入先出的顺序执行队列中的任务
  * */
  ExecutorService service3 = Executors.newSingleThreadExecutor(); // 单例线程池
  /*
  * 该方法也返回一个 ScheduledThreadPoolExecutor 对象，该线程池可以指定线程数量。
  * */
  ExecutorService service4 = Executors.newScheduledThreadPool(2);// 任务调度线程池


  static class MyTask implements Runnable {

    @Override
    public void run() {
      System.out
          .println(System.currentTimeMillis() + ": Thread ID :" + Thread.currentThread().getId());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    MyTask myTask = new MyTask();
    ThreadPoolExecutor service1 = new ThreadPoolExecutor(5, 20, 0L,
        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024)
        , Executors.defaultThreadFactory()
        , new AbortPolicy()
    );

    for (int i = 0; i < 10; i++) {
      service1.execute(myTask);
      service1.submit(myTask);
    }
    service1.shutdown();
  }

}

class A {

  public static void main(String[] args) {
    ScheduledThreadPoolExecutor service4 = (ScheduledThreadPoolExecutor) Executors
        .newScheduledThreadPool(2);

//    // 如果前面的任务没有完成，则调度也不会启动
//    service4.scheduleAtFixedRate(new Runnable() {
//      @Override
//      public void run() {
//        try {
//          // 如果任务执行时间大于间隔时间，那么就以执行时间为准（防止任务出现堆叠）。
//          Thread.sleep(10000);
//          System.out.println(System.currentTimeMillis() / 1000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//      }// initialDelay（初始延迟） 表示第一次延时时间 ; period 表示间隔时间
//    }, 0, 2, TimeUnit.SECONDS);
//
//
//    service4.scheduleWithFixedDelay(new Runnable() {
//      @Override
//      public void run() {
//        try {
//          Thread.sleep(5000);
//          System.out.println(System.currentTimeMillis() / 1000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//      }// initialDelay（初始延迟） 表示延时时间；delay + 任务执行时间 = 等于间隔时间 period
//    }, 0, 2, TimeUnit.SECONDS);

    // 在给定时间，对任务进行一次调度
    service4.schedule(new Runnable() {
      @Override
      public void run() {
        System.out.println("5 秒之后执行 schedule");
      }
    }, 5, TimeUnit.SECONDS);
  }

}

class AA {

  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    Field f = Unsafe.class.getDeclaredField("theUnsafe");
    f.setAccessible(true);
//    Unsafe unsafe = (Unsafe) f.get(null);
//    System.out.println(unsafe);

//    System.out.println(unsafe.getAddress(1));
    final int COUNT_BITS = Integer.SIZE - 3;

    System.out.println(COUNT_BITS);

    final int RUNNING = -1 << COUNT_BITS;
    final int SHUTDOWN = 0 << COUNT_BITS;
    final int STOP = 1 << COUNT_BITS;
    final int TIDYING = 2 << COUNT_BITS;
    final int TERMINATED = 3 << COUNT_BITS;

    System.out.println(Integer.toBinaryString(RUNNING));
    System.out.println(Integer.toBinaryString(SHUTDOWN));
    System.out.println(Integer.toBinaryString(STOP));
    System.out.println(Integer.toBinaryString(TIDYING));
    System.out.println(Integer.toBinaryString(TERMINATED));

  }
}
