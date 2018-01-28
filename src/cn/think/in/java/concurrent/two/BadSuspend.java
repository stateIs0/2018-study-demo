package cn.think.in.java.two;

public class BadSuspend {

  static Object u = new Object();
  static ChangeObjectThread t1 = new ChangeObjectThread("t1");
  static ChangeObjectThread t2 = new ChangeObjectThread("t2");


  static class ChangeObjectThread extends Thread {

    public ChangeObjectThread(String name) {
      super.setName(name);
    }

    public void run() {
      synchronized (u) {
        System.out.println("in " + getName());
        // 暂停
        Thread.currentThread().suspend();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    t1.start();
    Thread.sleep(100);
    // 此时 t1 已经暂停
    t2.start();
    // t1 恢复
    t1.resume();
    // t2 这时恢复，但是 t2在恢复之后进入了暂停，导致死锁。
    // 除非使用 sleep 让 t2 先暂停就可以。
//    Thread.sleep(100);
    t2.resume();
    t1.join();
    t2.join();

  }

}
