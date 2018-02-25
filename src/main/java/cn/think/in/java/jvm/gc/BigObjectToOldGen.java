package cn.think.in.java.jvm.gc;

public class BigObjectToOldGen {

  public static final int _1MB = 1024 * 1024;

  /**
   *
   * vm args : -XX:PretenureSizeThreshold=3145728 // 3M
   * -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xms20m -Xmx20m -Xmn10M -verbose:gc
   * -XX:+UseSerialGC //
   * -XX:+PrintFlagsFinal 打印所有gc 参数
   *
   *
   * -XX:+PrintGC 输出GC日志
     -XX:+PrintGCDetails 输出GC的详细日志
     -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
     -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
     -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
     -Xloggc:../logs/gc.log 日志文件的输出路径


   2014-07-18T16:02:17.606+0800（当前时间戳）: 611.633（时间戳）:
   [GC（表示Young GC） 611.633: [DefNew（单线程Serial年轻代GC）:
   843458K（年轻代垃圾回收前的大小）->2K（年轻代回收后的大小）(948864K（年轻代总大小）),
   0.0059180 secs（本次回收的时间）] 2186589K（整个堆回收前的大小）->1343132K（整个堆回收后的大小）
   (3057292K（堆总大小）), 0.0059490 secs（回收时间）]
   [Times: user=0.00（用户耗时） sys=0.00（系统耗时）, real=0.00 secs（实际耗时）]

   GC的日志是以替换的方式(>)写入的，而不是追加(>>)，如果下次写入到同一个文件中的话，以前的GC内容会被清空。
   */
  public static void main(String[] args) throws InterruptedException {
    System.out.println(Runtime.getRuntime().totalMemory());
    System.gc();
    byte[] allocation = new byte[3 *  1024 * 1024];// 直接分配在老年代中
  }

  /**
   * -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC
   *
   * VM_ARGS='-Xmx128m -Xms128m -Xmn64m -XX:+PrintGCDetails -XX:+PrintGCDateStamps  -Xloggc:$HOME/gc.log '
   */


  /**
   * -XX:PretenureSizeThreshold=3145728
   -XX:+PrintGCDetails
   -XX:SurvivorRatio=8
   -XX:MaxTenuringThreshold=15
   -Xms20m
   -Xmx20m
   -Xmn10M
   -verbose:gc
   -XX:+PrintFlagsFinal

   UseParallelGC
   */

}
