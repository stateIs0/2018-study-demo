package cn.think.in.java.two;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者和消费者
 */
public class PcDemo {

  boolean flag = true;

  List list = new ArrayList();

  synchronized void producer(Object ojb) throws InterruptedException {
    while (!flag) {
      wait();
    }
    list.add(ojb);

    if (list.size() > 10){
      wait();
    }
    System.out.println("add " + ojb);
  }


  synchronized void consumer() {
    list.clear();
    System.err.println("清空了");
    flag = true;
    notifyAll();
  }

  public static void main(String[] args) {

    PcDemo demo = new PcDemo();
    new Thread(new Producer(demo)).start();
    new Thread(new Consumer(demo)).start();
  }


}

class Producer implements Runnable {

  PcDemo pcDemo;


  public Producer(PcDemo pcDemo) {
    this.pcDemo = pcDemo;
  }

  @Override
  public void run() {
    try {
      int i = 0;
      for (; ; ) {
        pcDemo.producer(i++ + "");
        Thread.sleep(100);
      }
    } catch (Exception e) {

    }

  }
}

class Consumer implements Runnable {

  PcDemo pcDemo;

  public Consumer(PcDemo pcDemo) {
    this.pcDemo = pcDemo;
  }

  @Override
  public void run() {
    for (; ; ) {
      pcDemo.consumer();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
    }
  }
}
