package cn.think.in.java.learing.concurrent.lock;

import java.util.Random;

public class ThreadLocalDemo {

  static ThreadLocal threadLocal = new ThreadLocal();

  public static void main(String[] args) {

    threadLocal.set("hello");
    System.out.println(threadLocal.get());

    Random random = new Random();
    random.nextInt();
  }

}
