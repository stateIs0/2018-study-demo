package cn.think.in.java.jvm.gc;

public class TestZzm {

  public static final int _1MB = 1024 * 1024;

  /**
   * 最大堆20m，初始堆20m，新生代10m， 打印gc 详情，eden区8：1：1，打印gc时间戳
   *
   * vm args: -verbose:gc -Xmx20m -Xms20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+PrintGCDateStamps
   *
   * ps: -verbose:class 可以打印 jvm 加载类
   *
   *  XX:+PrintGC
     -XX:+PrintGCDetails
     -XX:+PrintGCDateStamps
     -Xloggc:C:UsersligjDownloadsgc.log

   Java HotSpot(TM) 64-Bit Server VM (24.80-b11) for windows-amd64 JRE (1.7.0_80-b15), built on Apr 10 2015 11:26:34 by "java_re" with unknown MS VC++:1600
   Memory: 4k page, physical 4184440k(823480k free), swap 8367040k(3693052k free)
   CommandLine flags: -XX:InitialHeapSize=805306368 -XX:MaxHeapSize=805306368 -XX:MaxNewSize=536870912 -XX:NewSize=536870912 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
   2016-01-22T01:42:26.325+0800: 8.799: [GC [PSYoungGen: 393216K->60289K(458752K)] 393216K->60361K(720896K), 0.1290115 secs] [Times: user=0.17 sys=0.00, real=0.13 secs]
   2016-01-22T01:42:35.849+0800: 18.322: [GC [PSYoungGen: 453505K->45971K(458752K)] 453577K->46043K(720896K), 0.1065373 secs] [Times: user=0.17 sys=0.00, real=0.11 secs]
   2016-01-22T01:42:48.524+0800: 30.999: [GC [PSYoungGen: 439187K->52498K(458752K)] 439259K->52578K(720896K), 0.1795032 secs] [Times: user=0.20 sys=0.00, real=0.18 secs]
   */
  public static void main(String args[]) {
    System.gc();
    byte[] allocation1, allocation2, allocation3, allocation4;

    allocation1 = new byte[2 * _1MB];
    allocation2 = new byte[2 * _1MB];
    allocation3 = new byte[2 * _1MB];
//    System.gc();

    allocation4 = new byte[4 * _1MB];// full GC
  }
  /*
  * log:
  * [GC (Allocation Failure) [DefNew: 7819K->659K(9216K), 0.0043342 secs] 7819K->6803K(19456K), 0.0043660 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     def new generation   total 9216K, used 4837K [0x07200000, 0x07c00000, 0x07c00000) // Young gen
      eden space 8192K,  51% used [0x07200000, 0x07614938, 0x07a00000)
      from space 1024K,  64% used [0x07b00000, 0x07ba4c88, 0x07c00000)
      to   space 1024K,   0% used [0x07a00000, 0x07a00000, 0x07b00000)
     tenured generation   total 10240K, used 6144K [0x07c00000, 0x08600000, 0x08600000) // Old gen
       the space 10240K,  60% used [0x07c00000, 0x08200030, 0x08200200, 0x08600000)
     Metaspace       used 233K, capacity 2280K, committed 2368K, reserved 4480K
  *
  *
  *
  *
  * 2018-01-27T09:53:22.012+0800: [Full GC (System.gc())  1675K->658K(19456K), 0.0024513 secs]
    2018-01-27T09:53:22.017+0800: [Full GC (System.gc())  6966K->6802K(19456K), 0.0044626 secs]

    2018-01-27T09:53:34.933+0800: [Full GC (System.gc())  1675K->633K(19456K), 0.0020091 secs]
    2018-01-27T09:53:34.937+0800: [GC (Allocation Failure)  7105K->6802K(19456K), 0.0031543 secs]
  * */

}
