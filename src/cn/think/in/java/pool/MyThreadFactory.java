package cn.think.in.java.pool;

import cn.think.in.java.pool.ThreadPoolDemo.MyTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程创建-----自己的线程工厂（记录线程的创建，设置为守护线程，当主线程退出时，将会强制销毁线程池）
 *
 * 我们还可以自定义线程的名称，组，优先级，是否为守护线程。总之可以自由的设置线程池
 */
public class MyThreadFactory {

  public static void main(String[] args) throws InterruptedException {
    MyTask myTask = new MyTask();
    ExecutorService service = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
        new SynchronousQueue<>(),
        new ThreadFactory() {
          @Override
          public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            System.out.println("create " + t.getName());
            return t;
          }
        });

    for (int i = 0; i < 5; i++) {
      service.submit(myTask);
    }

    Thread.sleep(2000);
  }

}
