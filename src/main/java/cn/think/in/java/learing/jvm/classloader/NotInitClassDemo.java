package cn.think.in.java.learing.jvm.classloader;

public class NotInitClassDemo {

  public static void main(String[] args) {
    SuperClass[] sca = new SuperClass[10];
    /**
     * 数组是个类
     */
  }
}

class ConstClass {

  static {
    System.out.println("ConstClass init");
  }

  public static final String helloWorld = "hello world";
}

class ConstClassDemo {

  /**
   * result hello world
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(ConstClass.helloWorld);
  }
}
