package cn.think.in.java.two;

/**
 * 守护线程，与之对应的是用户线程（工作线程），
 * 在一个Java 应用内，如果只有守护线程时，虚拟机就会自然退出。
 */
public class DaemonThread {

  static class DaemonT extends Thread {

    public void run() {
      for (; ; ) {
        System.out.println("I am alive");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }


  public static void main(String[] args) throws InterruptedException {
    Thread thread = new DaemonT();
    // 必须在 start 之前设置
    thread.setDaemon(true);
    // 设置线程优先级，并没有什么用，建议自己设计线程的调度
    thread.setPriority(1);
    thread.setPriority(5);
    thread.setPriority(10);
    thread.start();

//    main  是工作线程

    Thread.sleep(2000);
  }

}
