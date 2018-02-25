package cn.think.in.java.concurrent.one;

import java.util.Arrays;

public class LumadaTest {

  public static void main(String[] args) {
    int[] arr = {1,2,3,4,5,6,7,8,9};
    Arrays.stream(arr).forEach(System.out::print);

  }

}
