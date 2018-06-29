package cn.think.in.java.learing.tools.my.callback;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class MyCallback implements Callable {

  Operation operation;

  public MyCallback(Operation operation) {
    this.operation = operation;
  }

  public abstract void exec();

  @Override
  public Object call() throws Exception {
    try {

      exec();
      // doSomething

      operation.success();
    } catch (Exception e) {
      operation.fail();
    }

    return null;
  }
}


abstract class Operation {


  public abstract void success();

  public abstract void fail();


}


class Test {

  public static void main(String[] args) {
    ExecutorService pool = Executors.newSingleThreadExecutor();

    pool.submit(new MyCallback(new Operation() {
      @Override
      public void success() {
        System.out.println("success");
      }

      @Override
      public void fail() {
        System.err.println("fail");
      }
    }) {
      @Override
      public void exec() {
        System.out.println("hello");
        throw new RuntimeException();
      }
    });

  }
}
