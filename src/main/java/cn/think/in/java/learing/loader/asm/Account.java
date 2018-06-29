package cn.think.in.java.learing.loader.asm;


/**
 * Javassist（“Java Programming Assistant")
 */
public class Account {
  public void operation() {
    System.out.println("operation....");
    try {
      Thread.sleep(10);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class AccountMain1{

  public static void main(String[] args) throws InterruptedException {
    for (;;) {
      System.out.println("operation....1111");
      Thread.sleep(5000);
    }
  }
}


class AccountMain {

  public static void main(String[] args) throws InterruptedException {
    for (;;) {
      new Account().operation();
      Thread.sleep(5000);
    }

  }
}


class Bccount {

  static Account account = new Account();

  static void test() {
    System.out.print("bccount ");
    account.operation();
  }
}
