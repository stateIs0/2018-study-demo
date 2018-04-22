package cn.think.in.java.concurrent.pool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorDemo {

  static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
      1);

  public static void main(String[] args) {
    scheduledThreadPoolExecutor.schedule(new Runnable() {
      @Override
      public void run() {
        System.out.println("hello");
      }
    }, 1, TimeUnit.MILLISECONDS);


  }

}
