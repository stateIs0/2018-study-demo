package cn.think.in.java.jvm.gc;

import java.util.Map;

public class TestHeapGC {


  /**
   * vm args -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xms40m -Xmx40m -Xmn20M
   * @param args
   */
  public static void main(String[] args) throws InterruptedException {
    byte[] b1 = new byte[1024 * 1024 * 1];
    byte[] b2 = new byte[1024 * 1024 * 8];
//    logger.info("  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
    System.out.println(
        Runtime.getRuntime().maxMemory() / 1024 / 1024 + " " +
            Runtime.getRuntime().totalMemory() / 1024 / 1024 + " " +
            Runtime.getRuntime().freeMemory() / 1024 / 1024 + " " +
            (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()
                + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
    b2 = null;
    b2 = new byte[1024 * 1024 * 6]; // 进行一次新生代 GC

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

  /**
   * 1. ygc 后, 仍放不下: 晋升
   * 2. ygc 后, 放的下新的, 但老的无法进入 s 区:晋升
   *
   * 在YGC过程中，对象年纪达到阈值，正常晋升，或to空间不足，对象提前晋升，
   * 但老年代又没这么多空间容纳晋升上来的对象，这时会发生“promotion failed”，
   * 而且eden和from区的空间没办法清空， 把from区和to区进行swap，
   * 所以当前eden和from的使用率都是接近100%的，如果当前是给对象（非TLAB）申请内存，
   * 会继续触发一次老年代的回收动作

   */
}

class A{

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getStackTrace());
    while (true){

    }
  }
}
