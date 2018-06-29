package cn.think.in.java.learing.concurrent.tools;

import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link TimerThread.mainLoop 出现非 InterruptedException 就停止所有任务了}
 *
 * 由于只有一个线程，无法控制任务执行时间的精确
 *
 * 可使用 ScheduledExecutorService 代替
 *
 * delay : 延时
 * period : 周期
 *
 * 内部维护一个 TimerTask 数组，遍历数组，执行任务
 *
 *
 *
 * 为什么说不建议使用 Timer 做定时任务：
 *
 * 当任务异常了， 整个  Timer 都会结束。
 *
 * 如果完成某个计时器任务的时间太长，那么它会“独占”计时器的任务执行线程。因此，这就可能延迟后续任务的执行，
 * 而这些任务就可能“堆在一起”，并且在上述不友好的任务最终完成时才能够被快速连续地执行。
 *
 * 默认情况下（构造方法可修改），任务执行线程并不作为守护线程 来运行，所以它能够阻止应用程序终止。
 * 如果调用者想要快速终止计时器的任务执行线程，那么调用者应该调用计时器的 cancel 方法。
 *
 * 如果意外终止了计时器的任务执行线程，例如调用了它的 stop 方法，
 * 那么所有以后对该计时器安排任务的尝试都将导致 IllegalStateException，就好像调用了计时器的 cancel 方法一样。
 *
 */
public class TimerDemo {

  public static void main(String[] args) {
    Timer timer = new Timer();
    timer.schedule(new TimerDemo().new MyTask(), 1000, 2000);
  }

  class MyTask extends TimerTask {

    @Override
    public void run() {
      System.out.println("Timer 定时任务");
    }
  }

}

