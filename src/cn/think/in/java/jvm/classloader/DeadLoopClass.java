package cn.think.in.java.jvm.classloader;

public class DeadLoopClass {

  /**
   * 静态块只会执行一次，并且是同步的
   *
   */
  static {
    try {
      System.out.println("in DeadLoopClass");
      Thread.sleep(1000);
      new A2();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (true) {
      System.out.println(Thread.currentThread() + "init DealLoopClass");
    }
  }



}

class A2{
  /**
   * 静态块只会执行一次，并且是同步的
   *
   */
  static {
    try {
      System.out.println("in A2");
      Thread.sleep(1000);
      new DeadLoopClass();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (true) {
      System.out.println(Thread.currentThread() + "init A2");
    }
  }

}

class A{
  public static void main(String[] args) {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread() + " start");
        DeadLoopClass dlc = new DeadLoopClass();
        System.out.println(Thread.currentThread() + " run over");
      }
    };

    Thread t1 = new Thread(r);
    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread() + "start");
        A2 a2 = new A2();
      }
    });
    t1.start();
    t2.start();
  }

}

