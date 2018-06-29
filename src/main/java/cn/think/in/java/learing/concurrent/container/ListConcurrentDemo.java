package cn.think.in.java.learing.concurrent.container;

import java.util.ArrayList;

public class ListConcurrentDemo {

  static ArrayList<String> list = new ArrayList<>();

  public static void main(String[] args) {

    Thread a = new Thread(new A(), "A");
    Thread b = new Thread(new B(), "B");
    a.start();
    b.start();

  }


  static class A extends Thread {



    public void run() {
      for (int i = 0; i < 6; i++) {
        list.add("");
      }

    }
  }

  static class B extends Thread {

    public void run() {
      for (int i = 0; i < 6; i++) {
        list.add("");
      }

    }
  }
}
