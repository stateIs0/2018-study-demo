package cn.think.in.java.learing.jvm.gc;

public class JvmDemoTest {

  public static String toMemoryInfo(){

    int freeMemory;
    int totalMemory;
    String calcResult = null;
    Runtime runtime = Runtime.getRuntime();
    for (int i = 0; i < 100000; i++) {
      freeMemory = (int) (runtime.freeMemory() / 1024 / 1024);
      totalMemory = (int)(runtime.totalMemory() / 1024 / 1024);
      calcResult = freeMemory + "M/" + totalMemory + "M(free/ total)";
    }
    return calcResult;
  }


  /**
   * vm args -Xint or -Xcomp
   * 实际默认的模式更快......
   * @param args
   */
  public static void main(String[] args) {
    long b = System.currentTimeMillis();
    System.out.println("memory info : " + toMemoryInfo());
    System.out.println(System.currentTimeMillis() - b);
  }

}
