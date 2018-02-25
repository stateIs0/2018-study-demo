package cn.think.in.java.lambda;

import java.util.Arrays;

/**
 * 生成一个内部类 LambdaDemo$$Lambda$1，代码如下
 */
public class LambdaDemo {

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5};
    Arrays.stream(arr).forEach(System.out::println);
  }

}
/*
package cn.think.in.java.lambda;

import java.io.PrintStream;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.function.IntConsumer;

// $FF: synthetic class
final class LambdaDemo$$Lambda$1 implements IntConsumer {
  private final PrintStream arg$1;

  private LambdaDemo$$Lambda$1(PrintStream var1) {
    this.arg$1 = var1;
  }

  private static IntConsumer get$Lambda(PrintStream var0) {
    return new LambdaDemo$$Lambda$1(var0);
  }

  @Hidden
  public void accept(int var1) {
    this.arg$1.println(var1);
  }
}

*/
