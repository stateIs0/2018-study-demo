package cn.think.in.java.lambda;

public class ThreadLambdaDemo {

  public static void main(String[] args) {
    new Thread(() -> System.out.println("hello")).start();
  }

}

/*
package cn.think.in.java.lambda;

import java.lang.invoke.LambdaForm.Hidden;

// $FF: synthetic class
final class ThreadLambdaDemo$$Lambda$1 implements Runnable {
  private ThreadLambdaDemo$$Lambda$1() {
  }

  @Hidden
  public void run() {
    ThreadLambdaDemo.lambda$main$0();
  }
}

 */
