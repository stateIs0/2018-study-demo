package cn.think.in.java.learing.loader;

import java.util.Arrays;

public class ClassLoaderDemo {

  public static void main(String[] args) {
    int[] a = new int[1];
    int[] a1 = a;
    synchronized (a) {

      ClassLoaderDemo.class.getClassLoader().getResourceAsStream(ProxyDemo.Hello.class.getName());

    }
    Arrays.stream(a);
  }

}
