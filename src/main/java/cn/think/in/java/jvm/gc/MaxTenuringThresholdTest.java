package cn.think.in.java.jvm.gc;

public class MaxTenuringThresholdTest {

  public static final int _1MB = 1024 * 1024;

  /**
   * vm args = -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
   * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
   * //-XX:+UseParNewGC
   * -XX:+UseSerialGC
   */
  public static void main(String[] args) {
    byte[] allocation1, allocation2, allocation3;
    allocation1 = new byte[_1MB / 4];
    // 什么时候进入老年代取决于 XX:MaxTenuringThreshold
    allocation2 = new byte[4 * _1MB];
    allocation3 = new byte[4 * _1MB]; // 第一次gc
    allocation3 = null;
    allocation3 = new byte[4 * _1MB];// 第二次 gc

  }

  /**
   [GC (Allocation Failure) [DefNew
   Desired survivor size 524288 bytes, new threshold 1 (max 1)
   - age   1:     670112 bytes,     670112 total
   : 6242K->654K(9216K), 0.0045351 secs] 6242K->4750K(19456K), 0.0045700 secs] [Times: user=0.00 sys=0.01, real=0.00 secs]
   [GC (Allocation Failure) [DefNew
   Desired survivor size 524288 bytes, new threshold 1 (max 1)
   - age   1:       1312 bytes,       1312 total
   : 4832K->1K(9216K), 0.0013225 secs] 8928K->4739K(19456K), 0.0013374 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
   Heap
   def new generation   total 9216K, used 4235K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
   eden space 8192K,  51% used [0x00000007bec00000, 0x00000007bf0227f0, 0x00000007bf400000)
   from space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400520, 0x00000007bf500000)
   to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
   tenured generation   total 10240K, used 4738K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
   the space 10240K,  46% used [0x00000007bf600000, 0x00000007bfaa09d8, 0x00000007bfaa0a00, 0x00000007c0000000)
   Metaspace       used 3258K, capacity 4496K, committed 4864K, reserved 1056768K
   class space    used 362K, capacity 388K, committed 512K, reserved 1048576K
   */

  /**
   [GC (Allocation Failure) [DefNew
   Desired survivor size 524288 bytes, new threshold 1 (max 15)
   - age   1:     669504 bytes,     669504 total
   : 6219K->653K(9216K), 0.0045561 secs] 6219K->4749K(19456K), 0.0045853 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
   [GC (Allocation Failure) [DefNew
   Desired survivor size 524288 bytes, new threshold 15 (max 15)
   - age   1:        608 bytes,        608 total
   : 4831K->0K(9216K), 0.0015654 secs] 8927K->4738K(19456K), 0.0015907 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
   Heap
   def new generation   total 9216K, used 4234K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
   eden space 8192K,  51% used [0x00000007bec00000, 0x00000007bf022700, 0x00000007bf400000)
   from space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400260, 0x00000007bf500000)
   to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
   tenured generation   total 10240K, used 4737K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
   the space 10240K,  46% used [0x00000007bf600000, 0x00000007bfaa0760, 0x00000007bfaa0800, 0x00000007c0000000)
   Metaspace       used 3241K, capacity 4496K, committed 4864K, reserved 1056768K
   class space    used 359K, capacity 388K, committed 512K, reserved 1048576K
   */

}
