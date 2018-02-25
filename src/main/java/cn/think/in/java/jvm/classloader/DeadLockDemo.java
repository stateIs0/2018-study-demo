package cn.think.in.java.jvm.classloader;

public class DeadLockDemo {

  static class Aa {

    static synchronized void m() throws InterruptedException {

      System.out.println("in A");
      Thread.sleep(1000);
      Bb.m();
    }
  }

  static class Bb {

    static synchronized void m() throws InterruptedException {
      System.out.println("in b");
      Thread.sleep(1000);
      Aa.m();
    }
  }

  public static void main(String[] args) {
    new Thread(() -> {
      try {
        Aa.m();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        Bb.m();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }
}
