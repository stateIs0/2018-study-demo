package cn.think.in.java.clazz.loader.asm.stat.time;

public class TimeStat {

  static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

  public static void start() {
    threadLocal.set(System.currentTimeMillis());
  }

  public static void end() {
    long time = System.currentTimeMillis() - threadLocal.get();
    System.out.print(Thread.currentThread().getStackTrace()[2] + "方法耗费时间: ");
    System.out.println(time + "毫秒");

  }


}
