package cn.think.in.java.learing.jvm.gc;

public class RuntimeDemo {

  public static final int _1MB = 1024 * 1024;

  /**
   * vm args = -verbose:gc -Xms2048m -Xmx2048m  -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution  -XX:+PrintGCDateStamps -XX:+UseConcMarkSweepGC
   *
   *
   * @param args
   */
  public static void main(String[] args) {
    byte[] allocation1, allocation2, allocation3;
//    allocation1 = new byte[_1MB / 400];
    // 什么时候进入老年代取决于 XX:MaxTenuringThreshold
    allocation2 = new byte[200 * _1MB];
    allocation3 = new byte[200 * _1MB];
//    allocation3 = null;
    allocation3 = new byte[200 * _1MB];
  }

}
