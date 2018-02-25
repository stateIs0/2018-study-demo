package cn.think.in.java.jvm.gc;

public class TestGC {

  private byte[] bytes = new byte[1024 * 1024 * 10];

  /**
   * -verbosegc
   * @param args
   */
  public static void main(String[] args) {
    new TestGC();
    System.gc();

  }

}


class AA{

  public static void main(String[] args) throws InterruptedException {
    for (;;){
      System.out.println("cxs===");
    }
  }
}
