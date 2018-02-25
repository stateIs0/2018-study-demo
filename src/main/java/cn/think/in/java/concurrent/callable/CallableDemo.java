package cn.think.in.java.concurrent.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableDemo {

  String name;

  public CallableDemo(String name) {
    this.name = name;
  }


  @Override
  public String toString() {
    return "CallableDemo{" +
        "name='" + name + '\'' +
        '}';
  }

  public static void main(String[] args) throws Exception {
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    Task task = new Task();
    FutureTask<Integer> result = (FutureTask) executor.submit(task);
    executor.shutdown();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }

    System.out.println("主线程在执行任务");

    try {
      System.out.println("task运行结果" + result.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    System.out.println("所有任务执行完毕");
  }
}

class Task implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    System.out.println("子线程在进行计算");
    Thread.sleep(3000);
    int sum = 0;
    for (int i = 0; i < 100; i++) {
      sum += i;
    }
    return sum;
  }
}

class Task2 implements Callable<Future<Integer>> {

  @Override
  public Future<Integer> call() throws Exception {
    System.out.println("子线程在进行计算");
    Thread.sleep(3000);
    int sum = 0;
    for (int i = 0; i < 100; i++) {
      sum += i;
    }
    int finalSum = sum;

    return new Future<Integer>() {
      @Override
      public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
      }

      @Override
      public boolean isCancelled() {
        return false;
      }

      @Override
      public boolean isDone() {
        return false;
      }

      @Override
      public Integer get() throws InterruptedException, ExecutionException {
        return finalSum;
      }

      @Override
      public Integer get(long timeout, TimeUnit unit)
          throws InterruptedException, ExecutionException, TimeoutException {
        return finalSum;
      }
    };
  }
}

class AAAA{

  public static void main(String[] args) throws Exception {
    Callable callable = new Callable() {
      @Override
      public FutureTask<Object> call() throws Exception {
        FutureTask futureTask = new FutureTask(this);
        // 线程池中就是执行run方法
        futureTask.run();
        return futureTask;
      }
    };

    FutureTask futureTask = (FutureTask) callable.call();
    System.out.println(futureTask.get());




  }
};


class B{

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println(ThreadLocalRandom.current().nextInt(2));


      new Random().nextInt();
    }
  }
}
