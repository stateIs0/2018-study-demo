package cn.think.in.java.clazz.loader;

import java.util.Arrays;

public class ClassLoaderDemo {

  public static void main(String[] args) {
    int[] a = new int[1];
    int[] a1 = a;
    synchronized (a) {

    }
    Arrays.stream(a);
  }

}
