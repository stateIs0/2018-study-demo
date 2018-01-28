package cn.think.in.java.two;

public class ThreadGroupName implements Runnable {

  public static void main(String[] args) {
    ThreadGroup tg = new ThreadGroup("PrintGroup");
    Thread t1 = new Thread(tg, new ThreadGroupName(), "T1");
    Thread t2 = new Thread(tg, new ThreadGroupName(), "T2");
    t1.start();
    t2.start();
    System.out.println(tg.activeCount());// 答应大概的活动线程的总数
    tg.list();// 打印整个线程组中的所有线程信息
  }

  @Override
  public void run() {
    String groupName =
        Thread.currentThread().getThreadGroup().getName() + "-" + Thread.currentThread().getName();
    for (; ; ) {
      System.out.println("I am " + groupName);
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
