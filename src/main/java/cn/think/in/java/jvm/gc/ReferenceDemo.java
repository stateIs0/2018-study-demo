package cn.think.in.java.jvm.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceDemo {

  public static final int _1MB = 1024 * 1024;

  public static void main(String[] args) throws InterruptedException {
    ReferenceQueue<TraceCanReliveObj> phantomQueue = new ReferenceQueue<>();
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          for (; ; ) {
            Thread.sleep(1000);
            System.out.println(phantomQueue.remove() + " thread");
            System.out.println(Thread.currentThread().getName());
          }

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    );
    thread.start();

    WeakReference phantomRef = new WeakReference(thread, phantomQueue);
    thread.setName("hello name");
    thread.stop();
    thread = null;
    System.gc();

    Thread.sleep(1000);
    System.out.println(phantomQueue.remove() + " remove");


    System.gc();



  }


}

class TraceCanReliveObj {

  public static TraceCanReliveObj obj;

  static ReferenceQueue<TraceCanReliveObj> phantomQueue = null;

  static class CheckRefQueue extends Thread {

    public void run() {
      for (; ; ) {
        if (phantomQueue != null) {
          WeakReference<TraceCanReliveObj> objt = null;
          try {
            objt = (WeakReference<TraceCanReliveObj>) phantomQueue.remove();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          if (objt != null) {
            System.out.println(obj + "  1");
            System.out.println("TraceCanReliveObj is delete");
          }
        }
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread t = new CheckRefQueue();
    t.setDaemon(true);
    t.start();

    phantomQueue = new ReferenceQueue<>();
    obj = new TraceCanReliveObj();
    WeakReference phantomRef = new WeakReference<>(obj, phantomQueue);
    System.out.println(obj);
    Thread.sleep(1000);
    obj = null;
    System.gc();
    Thread.sleep(1000);
    if (obj == null) {
      System.out.println("obj is null");
    } else {
      System.out.println("obj is not null");
    }

    System.out.println("第二次gc");


  }


}
