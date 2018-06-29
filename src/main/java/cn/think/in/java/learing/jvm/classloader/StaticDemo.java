package cn.think.in.java.learing.jvm.classloader;

public class StaticDemo {

  static {
    i = 0;
//    System.out.println(i); // compile fail;
  }

  static int i = 1;

  public static void main(String[] args) {
    System.out.println(i);
  }
}
