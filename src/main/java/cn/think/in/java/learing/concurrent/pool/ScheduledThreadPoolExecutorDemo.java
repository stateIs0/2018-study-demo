package cn.think.in.java.learing.concurrent.pool;

import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorDemo {

  static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
      1);

  public static void main(String[] args) {
    ScheduledFuture future = scheduledThreadPoolExecutor.schedule(new Runnable() {
      @Override
      public void run() {
        System.out.println("hello");
      }
    }, 1, TimeUnit.MILLISECONDS);

    Future future1 = Executors.newFixedThreadPool(1).submit(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        return null;
      }
    });

   ThreadPoolExecutor t =  new ThreadPoolExecutor(1, 2, 0L, null, null);
    t.allowsCoreThreadTimeOut();
    t.prestartAllCoreThreads();

    future1.cancel(false);



    future.cancel(true);

    TreeSet treeSet = new TreeSet();
    treeSet.add("hello");

  }

}
