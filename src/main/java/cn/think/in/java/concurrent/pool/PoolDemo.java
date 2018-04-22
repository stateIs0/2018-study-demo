package cn.think.in.java.concurrent.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/**
 * 拒绝策略：
 * 1. {@linkplain  java.util.concurrent.ThreadPoolExecutor.AbortPolicy} 直接抛出异常，阻止系统正常工作
 *
 * 2. {@linkplain java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy}
 *                                                                          只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。
 *                                                                          显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降。
 * 3. {@linkplain java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy}
 *                                                                            该策略将丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务
 * 4. {@linkplain java.util.concurrent.ThreadPoolExecutor.DiscardPolicy}
 *                                                                     该策略默默地丢弃无法处理的任务，不予任何处理，如果允许任务丢失，我觉得这是最好的方案
 * 5.{@linkplain java.util.concurrent.RejectedExecutionHandler}
 *                                                            如果以上4个策略无法满足，可以自己实现该接口
 */


/**
 * 对列：
 * 1. {@linkplain LinkedBlockingQueue} 无界队列，会耗尽系统内存
 * 2. {@linkplain SynchronousQueue} Cache 直接提交的队列；没有容量，不会保存，直接创建新的线程，因此需要设置很大的线程池数。否则容易执行拒绝策略
 * 3. {@linkplain DelayedWorkQueue} Scheduled 专业延迟策略，只能存储 RunnableScheduledFuture 这个类必须声明为一个BlockingQueue
 * 4. {@linkplain java.util.concurrent.ArrayBlockingQueue} 表示该队列的最大容量，如果core满了，则存储在队列中，如果core满了且队列满了，则创建线程，直到maxcore到了，如果队列满了且最大线程数已经到了，则执行拒绝策略。
 * 5. {@linkplain java.util.PriorityQueue} 优先级队列
 */
public class PoolDemo {

  /**
   * 默认5条线程（默认数量，即最少数量），
   * 最大20线程（指定了线程池中的最大线程数量），
   * 空闲时间0秒（当线程池梳理超过核心数量时，多余的空闲时间的存活时间，即超过核心线程数量的空闲线程，在多长时间内，会被销毁），
   * 等待队列长度1024，
   * 线程名称[MXR-Task-%d],方便回溯，
   * 拒绝策略：当任务队列已满，抛出RejectedExecutionException
   * 异常。
   */
  private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 20, 0L,
      TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024)
//      , new ThreadFactoryBuilder().setNameFormat("My-Task-%d").build()
      , Executors.defaultThreadFactory()
      , new AbortPolicy()
  );

  /**
   * 执行无返回值任务
   *
   * @param runnable 线程
   */
  public static void execute(Runnable runnable) {
    threadPool.execute(runnable);
  }

  /**
   * 执行有返回值任务
   *
   * @param runnable 回调任务
   * @param <T> 泛型
   * @return 回调返回值
   */
  public static <T> Future<T> submit(Callable<T> runnable) {
    return threadPool.submit(runnable);
  }

  /**
   * 停止提交新任务，并且完成所有未执行完毕的任务，包括队列中的。
   */
  public static void shutdown() {
    threadPool.shutdown();
  }

  /**
   * 停止提交新任务，并且清除所有未执行的任务。
   */
  public static void shutdownNow() {
    threadPool.shutdownNow();
  }

  ThreadFactory threadFactory;
  ThreadGroup threadGroup;
  Thread thread;
  ThreadLocal threadLocal;
  ThreadPoolExecutor threadPoolExecutor;
  ThreadLocalRandom threadLocalRandom;


}
