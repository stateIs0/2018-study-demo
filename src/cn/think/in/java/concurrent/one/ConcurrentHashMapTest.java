package cn.think.in.java.concurrent.one;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapTest {


  public static void main(String[] args) throws InterruptedException {
    ConcurrentHashMap map = new ConcurrentHashMap();
    for (int i = 0; i < 100; i++) {
      int a = i;
      new Thread(() -> {
        try {
          map.put(a, "");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }).start();
    }

    for (int i = 0; i < 10; i++) {
      if (i == 9){
        Thread.sleep(10);
      }
      System.out.println(map.size());
    }


  }

  public static void main1(String[] args) {
    System.out.println(4 >>> 3);
  }

}

class AAA {

  public String[] createStrings(String[] args) {
    Vector<String> v = new Vector<>();
    for (int i = 0; i < 100; i++) {
      v.add(Integer.toString(i));
    }
    return v.toArray(new String[]{});
  }

  public static void main(String[] args) throws InterruptedException {
    AtomicInteger integer = new AtomicInteger();
    System.out.println(integer.get());


    Thread[] threads = new Thread[1000];

    for (int j = 0; j < 1000; j++) {
      threads[j] = new Thread(() ->
          integer.incrementAndGet()
      );
      threads[j].start();
    }

    for (int j = 0; j < 1000; j++) {
      threads[j].join();
    }

    System.out.println(integer.get());
  }
}


class Aaaa{

  public static void main(String[] args) throws InterruptedException {
    LinkedBlockingQueue queue = new LinkedBlockingQueue();
    queue.put("1");
    queue.put("2");
    queue.put("3");
    queue.take();

    int a,b,c;
    a = 1;
    b = 2;
    c = 3;
    a = b = c;
    System.out.println(a + "" + b + c);

    Node node = new Node();
    node.item = new Object();
    node.next = new Node();



  }
}

class Node<V> {

  V item;

  Node next;

}
