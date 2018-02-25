package cn.think.in.java.jvm.classloader;

public class SuperClass {

  static {
    System.out.println("SuperClass init !");
  }

  public static int value = 123;

  static class SubClass extends SuperClass {

    static {
      System.out.println("Subclass init !");
    }

  }

  static class NotInitialization {

    public static void main(String[] args) {
      System.out.println(SuperClass.value);
    }

    /**
     * result
     *
     * SuperClass init !
       123
     */
  }

}
