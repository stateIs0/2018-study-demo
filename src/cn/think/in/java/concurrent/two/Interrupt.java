package cn.think.in.java.two;

public class Interrupt {

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(() -> {
      for (; ; ) {
      }
    });
    t1.start();
    Thread.sleep(2000);
    // 不会起任何作用，所以需要判断他的中断位状态
    t1.interrupt();
  }

}

class Interrupt2 {

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(() -> {
      for (; ; ) {
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("interrupt");
          break;
        }
        Thread.yield();
      }
    });
    t1.start();
    Thread.sleep(2000);
    t1.interrupt();
  }

}


class Interrupt3 {

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(() -> {
      for (; ; ) {
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("interrupt");
          break;
        }
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          System.err.println("Interrupt When Sleep");
          // 由于在 sleep 之间中断线程导致抛出异常，此时，他会清楚中断位，所以需要在这里重新设置中断位，下次循环则会直接判断中断标记，从而break。
          Thread.currentThread().interrupt();
          // 该方法会清除中断状态，导致上面的一行代码失效
//          boolean isInterrupt = Thread.interrupted();
//          System.out.println(isInterrupt);
        }
        Thread.yield();
      }
    });

    t1.start();
    Thread.sleep(1000);
    t1.interrupt();
  }

}

