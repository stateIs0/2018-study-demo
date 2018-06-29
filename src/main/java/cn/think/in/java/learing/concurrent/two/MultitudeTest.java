package cn.think.in.java.two;

/**
 * volatile 不能保证原子性，只能遵守 hp 原则 保证单线程的有序性和可见性。
 */
public class MultitudeTest {

  static volatile int i = 0;

  static class PlusTask implements Runnable {

    @Override
    public void run() {
      for (int j = 0; j < 10000; j++) {
//        plusI();
        i =j;
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread[] threads = new Thread[10];
    for (int j = 0; j < 10; j++) {
      threads[j] = new Thread(new PlusTask());
      threads[j].start();
    }

    for (int j = 0; j < 10; j++) {
      threads[j].join();
    }

    System.out.println(i);
  }

//  static synchronized void plusI() {
//    i++;
//  }

}
