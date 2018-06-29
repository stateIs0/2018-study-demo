package cn.think.in.java.learing.collection;

public class FinalizeTest {

  public static void main(String[] args) {
    FinalizeTest f = new FinalizeTest();
    f = null;
    System.gc();
  }

  @Override
  protected void finalize() throws Throwable {
    System.out.println("finalize");

  }
}
