package cn.think.in.java.learing.concurrent.pool.pool;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 *  Fork/Join 核心思想：分而治之
 *
 * 著名的 MapReduce 也是这个思想。将任务进行分解，然后合并所有的结果。
 *
 *
 */
public class CountTask extends RecursiveTask<Long> {

  /**
   * 阈值
   */
  static final int THRESHOLD = 10000;
  long start;
  long end;

  public CountTask(long start, long end) {
    this.start = start;
    this.end = end;
  }

  /**
   * 有返回值的
   * @return
   */
  @Override
  protected Long compute() {

    long sum = 0;
    // 当阀值小于10000则不分解了
    boolean canCompute = (end - start) < THRESHOLD;
    if (canCompute) {
      for (long i = start; i <= end; i++) {
        sum += i;
      }
    } else {
      // 2000
      long step = (start + end) / 100;
      ArrayList<Object> subTasks = new ArrayList<>();
      long pos = start;
      for (int i = 0; i < 100; i++) {
        long lastOne = pos + step;
        if (lastOne > end) {
          lastOne = end;
        }
        //0-2000 个计算任务 * 100
        CountTask subTask = new CountTask(pos, lastOne);
        pos += step + 1;
        subTasks.add(subTask);
        subTask.fork();// fork
      }

      for (Object a : subTasks) {
        CountTask t = (CountTask)a;
        sum += t.join();
      }
    }
    return sum;

  }

  public static void main(String[] args) {

    ForkJoinPool forkJoinPool = new ForkJoinPool();
    CountTask task = new CountTask(0, 200000L);
    // 将一个大的任务提交到池中
    ForkJoinTask<Long> result = forkJoinPool.submit(task);
    long res = 0;
    try {
      // 等待运算结果
      res = result.get();
      System.out.println("sum = " + res);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }

  }
}


class CountAction extends RecursiveAction {

  /**
   * 无返回值的计算任务
   */
  @Override
  protected void compute() {

  }
}
