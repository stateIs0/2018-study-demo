package cn.think.in.java.learing.concurrent.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompletableFutureDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuture c = new CompletableFuture();

    new Thread(() ->
    {
      try {
        System.out.println(c.get() + " " + Thread.currentThread().getName());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
    ).start();

    c.complete("hello");

/******************************************************************/

/*
 默认线程池都是 daemon 线程，主线程退出后，其他线程都会退出。
 */

    CompletableFuture future = CompletableFuture.supplyAsync(() -> calc(50));
    future = CompletableFuture.runAsync(new Runnable() {
      @Override
      public void run() {
        System.out.println("hello " + Thread.currentThread().getName());
      }
    });
//    future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
//      @Override
//      public Integer get() {
//        return 1;
//      }
//    });
//    System.out.println(future.get());

  }

  static int calc(int para) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();

    }
    return para * para;
  }


}
