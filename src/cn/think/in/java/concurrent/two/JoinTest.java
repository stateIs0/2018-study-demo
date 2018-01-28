package cn.think.in.java.two;

public class JoinTest {

  static int i;

  public static void main(String[] args) throws InterruptedException {
    AddThread addThread = new AddThread();
    addThread.start();
    // 主函数等待 addThread
    // join 的本质是调用了 wait方法，让调用线程 wait 在当前线程对象实例上。也就是main线程 wait 在 addThread 线程实例上。
    // 当 addThread 执行结束后，会调用 notifyAll 方法，注意，不要再程序中调用线程的 wait 或者 notify 方法，
    // 可能会影响系统API 的工作。
    addThread.join();// 重载方法 join（long） 如果达到给定的毫秒数，则不等了
    System.out.println(i);
  }


  static class AddThread extends Thread {

    public void run() {
      for (; i < 10000000; i++) {
      }
    }
  }

}
