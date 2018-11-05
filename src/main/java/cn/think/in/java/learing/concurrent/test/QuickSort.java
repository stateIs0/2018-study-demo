package cn.think.in.java.learing.concurrent.test;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QuickSort {

  public static void main(String[] args) {
    int[] a = {12, 3, 4, 5, 3, 1, 7, 0, 122};
    System.out.println(Arrays.toString(a));
    quickSort(a);
    System.out.println(Arrays.toString(a));


  }

  public static void quickSort(int[] a) {
    if (a.length > 0) {
      quickSort(a, 0, a.length - 1);
    }
  }

  /**
   * 比 key 小的放左边，比 key 大的放右边。
   */
  private static void quickSort(int[] a, int low, int high) {
    //1,找到递归算法的出口
    if (low > high) {
      return;
    }
    //2, 存
    int i = low;
    int j = high;
    //3,key
    int key = a[low];
    //4，完成一趟排序
    while (i < j) {
      //4.1 ，从右往左找到第一个小于key的数
      while (i < j && a[j] > key) {
        j--;
      }
      // 4.2 从左往右找到第一个大于key的数
      while (i < j && a[i] <= key) {
        i++;
      }
      //4.3 交换
      if (i < j) {
        int p = a[i];
        a[i] = a[j];
        a[j] = p;
      }
    }
    // 4.4，调整key的位置
    int p = a[i];
    a[i] = a[low];
    a[low] = p;

    System.out.println(Arrays.toString(a));
    //5, 对key左边的数快排
    quickSort(a, low, i - 1);
    //6, 对key右边的数快排
    quickSort(a, i + 1, high);
  }

  void f(int[] a, int low, int hight) {
    if (low > hight) {
      return;
    }
    int i = low;
    int j = hight;

    int key = a[low];
    while (i < j) {
      while (i < j && a[j] > key) {
        j--;
      }
      while (i < j && a[i] >= key) {
        i++;
      }

      if (i < j) {
        int p = a[i];
        a[i] =a[j];
        a[j] = p;
      }
    }

    int p = a[i];
    a[i] = a[low];
    a[low] = p;

    quickSort(a, low, i - 1);
    quickSort(a, i + 1, hight);
  }
}









class A {

  static void f(int[] a, int low, int high) {
    if (low > high) {
      return;
    }
    int i = a[low];
    int j = a[high];

    int key = a[low];

    while (i < j) {
      while (i < j && a[j] < key) {
        j--;
      }
      while (i < j && a[i] >= key) {
        i++;
      }
      if (i < j) {
        int p = a[i];
        a[i] = a[j];
        a[j] = p;
      }
    }
    int p = a[low];
    a[low] = a[i];
    a[i] = p;

    f(a, low, i - 1);
    f(a, i + 1, high);


  }

  public static void main(String[] args) {
    int[] a = {12, 3, 4, 5, 3, 1, 7, 0, 122};
    f(a, 0, a.length - 1);
    System.out.println(Arrays.toString(a));

  }

}























