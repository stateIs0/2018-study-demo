package cn.think.in.java.learing.concurrent.pool.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 如何扩展线程池，重写 beforeExecute, afterExecute, terminated 方法，这三个方法默认是空的。
 *
 * 可以监控每个线程任务执行的开始和结束时间，或者自定义一些增强。
 *
 * 在 Worker 的 runWork 方法中，会调用这些方法
 */
public class ExtendThreadPoolDemo {

  static class MyTask implements Runnable {

    String name;

    public MyTask(String name) {
      this.name = name;
    }

    @Override
    public void run() {
      System.out
          .println("正在执行：Thread ID:" + Thread.currentThread().getId() + ", Task Name = " + name);
      try {
        Thread.sleep(1002);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }


  }


  public static void main(String[] args) throws InterruptedException {
    ThreadPoolExecutor es = new ThreadPoolExecutor(5, 11, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>()) {
      @Override
      protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("准备执行：" + ((MyTask) r).name);
      }

      @Override
      protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("执行完成： " + ((MyTask) r).name);
      }

      @Override
      protected void terminated() {
        System.out.println("线程池退出");
      }
    };




    for (int i = 0; i < 52; i++) {
      MyTask myTask = new MyTask("TASK-GEYM-" + i);
      es.execute(myTask);


    }
    Thread.sleep(3000);

    for (int i = 0; i < 5; i++) {
      MyTask myTask = new MyTask("TASK-GEYM-CXS" + i);
      es.submit(myTask);

    }
    es.shutdown();
  }

}
