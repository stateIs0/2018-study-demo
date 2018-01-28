package cn.think.in.java.tools;

import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link TimerThread.mainLoop 出现非 InterruptedException 就停止所有任务了}
 *
 * 由于只有一个线程，无法控制任务执行时间的精确
 *
 * 可使用 ScheduledExecutorService 代替
 *
 * 内部维护一个 TimerTask 数组，遍历数组，执行任务
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

