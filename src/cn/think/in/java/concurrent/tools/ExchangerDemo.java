package cn.think.in.java.tools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  线程协作工具类
 *
 *  用于进行线程间的数据交换。他提供一个同步点，两个线程可以交换彼此的数据。
 *  这两个线程通过 exchange 方法交换数据，如果第一个线程先执行 exchanger方法，他会一直等待第二个线程也执行 exchanger 方法，
 *  当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。
 *
 *  exchanger 可用于遗传算法。遗传算法里需要选出两个人作为交配对象。这时候会交换两人的数据。并使用交叉规则得出两个交配结果。
 *
 *  exchanger 也可用于校对工作，比如银行流水。
 *
 *  如果担心 exchanger 时间过长，可以设置过长时间  exchange(V x, long timeout, TimeUnit unit)
 */
public class ExchangerDemo {

  static final Exchanger<String> exgr = new Exchanger<>();

  static ExecutorService threadPool = Executors.newFixedThreadPool(2);

  public static void main(String[] args) {
    threadPool.execute(new Runnable() {
      @Override
      public void run() {
        try {
          String a = "银行流水A";
          String b = exgr.exchange(a);
          System.err.println("A 和 B 数据是否一致： " + a.equals(b) + ", A 录入的是：" + a + ", B 录入的是：" + b);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    threadPool.execute(new Runnable() {
      @Override
      public void run() {
        try {
          String b = "银行流水B";
          String a = exgr.exchange(b);
          System.out.println("A 和 B 数据是否一致： " + a.equals(b) + ", A 录入的是：" + a + ", B 录入的是：" + b);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    threadPool.shutdown();
  }

}
