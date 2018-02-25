package cn.think.in.java.jvm.classloader;

public class Parent {

  static int A = 1;

  static {
    A = 2;
//    System.out.println(i); // compile fail;
  }


  static class Sub extends Parent {

    public static final int B = A;
  }

  public static void main(String[] args) {
    System.out.println(Sub.B);
  }

}
