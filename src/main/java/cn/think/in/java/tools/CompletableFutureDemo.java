package cn.think.in.java.tools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

  public static void mai1n(String[] args) {
    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
      System.err.println("Hello");
    });

    try {
      future.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    System.err.println("CompletableFuture");

    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() ->
        "Hello");

    try {
      System.out.println(future2.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    System.out.println("CompletableFuture");
  }


  public static void ma2in(String[] args) throws InterruptedException {
    CompletableFuture<String> future  = CompletableFuture.supplyAsync(() -> "Hello");

//    Thread.sleep(3000);// 如果上面的方法完成了，complete 就无效了

    future.complete("World");

    try {
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    CompletableFuture<String> future  = CompletableFuture.supplyAsync(() -> "Hello");

//    Thread.sleep(3000);// 如果上面的方法完成了，complete 就无效了

    future.completeExceptionally(new RuntimeException("cxs"));

    try {
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }


}
























































