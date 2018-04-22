package cn.think.in.java.concurrent.pool.pool;

import java.util.TimerTask;

public class MyTask extends TimerTask {

  @Override
  public void run() {
    System.out.println("Timer 定时任务");
  }
}

