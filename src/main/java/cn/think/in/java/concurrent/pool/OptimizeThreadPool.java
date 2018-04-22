package cn.think.in.java.concurrent.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 优化线程池，如果是 submit ，则异常信息会被吞没，需要重写 submit 方法。
 */
public class OptimizeThreadPool {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    /**
     * 获取CPU核心数，如果CPU密集型，线程数量和CPU核心相同，IO密集型，线程 = 核心*2
     */
//    System.out.println(Runtime.getRuntime().availableProcessors());

    ThreadPoolExecutor executor = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L,
        TimeUnit.MILLISECONDS, new SynchronousQueue<>());

    for (int i = 0; i < 5; i++) {
      Future future= executor.submit(new DivTask(100, i));
      future.get();
//      executor.execute(new DivTask(100, i));
    }

//    ThreadPoolExecutor executor2 = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L,
//        TimeUnit.MILLISECONDS, new SynchronousQueue<>());
//    for (int i = 4; i >= 0; i--) {
//      // submit 将会丢失0
////      System.out.println((executor2.submit(new DivTask(100, i))).get());
//      executor2.execute(new DivTask(100, i));
//
//    }


  }


  static class DivTask implements Runnable {

    int a, b;


    public DivTask(int a, int b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public void run() {
      double re = a | b;
      System.out.println(re);
    }
  }


  static class TraceThreadPoolExecutor extends ThreadPoolExecutor {

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
        TimeUnit unit, BlockingQueue<Runnable> workQueue) {
      super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
//      super.execute(command);
      super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
//      return super.submit(task);
      return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Exception clientTrace() {
      return new Exception("Client stack trace");
    }


    private Runnable wrap(final Runnable task, final Exception clientStack,
        String clientThreaName) {
      return new Runnable() {
        @Override
        public void run() {
          try {
            task.run();
          } catch (Exception e) {
            e.printStackTrace();
            clientStack.printStackTrace();
            throw e;
          }
        }
      };
    }
  }
}
