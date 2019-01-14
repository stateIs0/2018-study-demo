package cn.think.in.java.learing.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 允许多个线程同时访问: 信号量（Semaphore）
 * 广义上说：信号量是对锁的扩展。锁一次只能允许一个线程访问一个资源。而信号量却可以指定多个线程，同时访问某一个资源。共享锁。
 */
public class SemaphoreTest implements Runnable {

  // 需要指定信号量的准入数，相当于指定了同时有多少个线程可以同时访问某一个资源
  final Semaphore semaphore = new Semaphore(5);

  @Override
  public void run() {
    try {
      semaphore.acquire();
      // 模拟耗时操作
      Thread.sleep(2000);
      System.out.println(Thread.currentThread().getId() + ":done!");
      semaphore.release();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ExecutorService exec = Executors.newFixedThreadPool(20);
    final SemaphoreTest test = new SemaphoreTest();
    for (int i = 0; i < 20; i++) {
      exec.execute(test);
    }
  }

  public static void main0(String[] args) {
    System.out.println(255 << 1);
    System.out.println(4 >>> 1);
    System.out.println(1  << 10);
  }
}

class A extends SemaphoreTest{

}


/**
 *
 构造方法摘要
 Semaphore(int permits)
 创建具有给定的许可数和非公平的公平设置的 Semaphore。
 Semaphore(int permits, boolean fair)
 创建具有给定的许可数和给定的公平设置的 Semaphore。

 acquire()
 从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。

 release()



 释放一个许可，将其返回给信号量。

 acquireUninterruptibly()
 从此信号量中获取许可，在有可用的许可前将其阻塞。

 boolean	tryAcquire()
 仅在调用时此信号量存在一个可用许可，才从信号量获取许可。

 boolean	tryAcquire(int permits)
 仅在调用时此信号量中有给定数目的许可时，才从此信号量中获取这些许可。

 boolean	tryAcquire(int permits, long timeout, TimeUnit unit)
 如果在给定的等待时间内此信号量有可用的所有许可，并且当前线程未被中断，则从此信号量获取给定数目的许可。

 isFair()
 如果此信号量的公平设置为 true，则返回 true。


 下面是阻塞方法

 public void acquire() throws InterruptedException {  }     //获取一个许可
 public void acquire(int permits) throws InterruptedException { }    //获取permits个许可
 public void release() { }          //释放一个许可
 public void release(int permits) { }    //释放permits个许可

 下面是非阻塞方法

 public boolean tryAcquire() { };    //尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
 public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };  //尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
 public boolean tryAcquire(int permits) { }; //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
 public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { }; //尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
 */
