package cn.think.in.java.two;

public class WaitTest {

  boolean flag;

  public static void main(String[] args) {

    WaitTest waitTest = new WaitTest();

    new Thread(() -> {
      try {
        waitTest.test();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
//    new Thread(() -> {
//      try {
//        waitTest.test();
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }).start();

  }


  synchronized void test() throws InterruptedException {
    if (!flag) {
      long s = System.currentTimeMillis();
      System.out.println("flag is false, wait it and modify flag is true");
      flag = true;
      this.wait(1000);
      long e = System.currentTimeMillis();
      long r = e - s;
      System.out.println("被唤醒 继续运行" + r);
    } else {
      System.out.println("flag is true, notify it");
//      this.notify();
    }
  }

}

class A1 {

  synchronized void a() {
    System.out.println("father");
  }
}

class A2 extends A1 {

  @Override
  synchronized void a() {
    System.out.println("son/");
    super.a();
  }


  public static void main(String[] args) {
    new A2().a();
  }
}
