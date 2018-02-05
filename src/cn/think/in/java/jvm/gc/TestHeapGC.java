package cn.think.in.java.jvm.gc;

import java.util.Map;

public class TestHeapGC {

  /**
   * vm args -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xms40m -Xmx40m -Xmn20M
   * @param args
   */
  public static void main(String[] args) throws InterruptedException {
    byte[] b1 = new byte[1024 * 1024 / 2];
    byte[] b2 = new byte[1024 * 1024 * 8];
    b2 = null;
    b2 = new byte[1024 * 1024 * 8]; // 进行一次新生代 GC

//    System.gc();

    for (Map.Entry<Thread, StackTraceElement[]> a:Thread.getAllStackTraces().entrySet()){
      Thread thread = a.getKey();
      StackTraceElement [] s = a.getValue();
      System.out.println(thread.getName());
      for(StackTraceElement e : s){
        System.out.println(e.getClassName());
      }
      System.out.println();
    }
  }

}
