package cn.think.in.java.learing.concurrent.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPrintDemo {


  static AtomicInteger cxsNum = new AtomicInteger(0);
  static volatile boolean flag = false;

  public static void main(String[] args) {

    Thread t1 = new Thread(() -> {
      for (; 100 > cxsNum.get(); ) {
        if (!flag && (cxsNum.get() == 0 || cxsNum.incrementAndGet() % 2 == 0)) {


          System.out.println(cxsNum.get());
          flag = true;
        }
      }
    }
    );

    Thread t2 = new Thread(() -> {
      for (; 100 > cxsNum.get(); ) {
        if (flag && (cxsNum.incrementAndGet() % 2 != 0)) {

          System.out.println(cxsNum.get());
          flag = false;
        }
      }
    }
    );

    t1.start();
    t2.start();
  }


}

class ThreadPrintDemo2 {

  public static void main(String[] args) {
    final ThreadPrintDemo2 demo2 = new ThreadPrintDemo2();
    Thread t1 = new Thread(demo2::print1);
    Thread t2 = new Thread(demo2::print2);

    t2.start();
    t1.start();

  }

  public synchronized void print2() {
    for (int i = 1; i <= 100; i += 2) {
      System.out.println(i);
      this.notify();
      try {
        this.wait();
        Thread.sleep(100);// 防止打印速度过快导致混乱
      } catch (InterruptedException e) {
        // NO
      }

    }
  }

  public synchronized void print1() {
    for (int i = 0; i <= 100; i += 2) {
      System.out.println(i);
      this.notify();
      try {
        this.wait();
        Thread.sleep(100);// 防止打印速度过快导致混乱
      } catch (InterruptedException e) {
        // NO
      }

    }
  }
}


class ThreadPrintDemo3 {

  static int num = 0;
  static volatile boolean flag = false;

  public static void main(String[] args) {

    Thread t1 = new Thread(() -> {
      for (; 100 > num; ) {
        if (!flag && (num == 0 || ++num % 2 == 0)) {
          System.out.println(num);
          flag = true;
        }
      }
    }
    );

    Thread t2 = new Thread(() -> {
      for (; 100 > num; ) {
        if (flag && (++num % 2 != 0)) {
          System.out.println(num);
          flag = false;
        }
      }
    }
    );

    t1.start();
    t2.start();

  }
}


class ReverseDemo {

  public static void main(String[] args) {

    String test = "abcdefg";

    System.out.println(new StringBuilder(test).reverse());

    char[] arr = test.toCharArray();

    for (int i = arr.length - 1; i >= 0; i--) {
      System.out.print(arr[i]);
    }

  }
}

class LockDemo {

  static int a = 1;

  public static void main(String[] args) {
    Thread tb = new Thread(() -> {
      a = 2;
    });
    Thread ta = new Thread(() -> {
      try {
        tb.join();
      } catch (InterruptedException e) {
        //NO
      }
      System.out.println(a);
    });

    ta.start();
    tb.start();


  }


}

class HBTest {

  static int a = 1;

  public static void main(String[] args) {
    Thread tb = new Thread(() -> {
      System.out.println(a);
    });
    Thread ta = new Thread(() -> {
      tb.start();
      a = 2;
    });


    ta.start();

  }
}



























