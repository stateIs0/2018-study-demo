package ali;

import java.io.FileNotFoundException;
import java.util.concurrent.ForkJoinPool;

public class Demo {


  public static void main(String[] args) throws FileNotFoundException {
    sort(null,1,1 );
  }

  public static void sort(int[] a, int low, int high) throws FileNotFoundException {
    ForkJoinPool pool = new ForkJoinPool();
    pool.execute(
        (Runnable) null);

  }

}
