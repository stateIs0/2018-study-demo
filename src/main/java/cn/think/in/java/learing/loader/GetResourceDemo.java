package cn.think.in.java.learing.loader;

public class GetResourceDemo {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
    System.out.println(GetResourceDemo.class.getResource(""));
    System.out.println(GetResourceDemo.class.getResource("/"));

  }

}
