package cn.think.in.java.learing.concurrent.test;

import java.util.Arrays;
import java.util.concurrent.Executors;

public class QuickSort {

  public static void main(String[] args) {
    int[] a = {1, 2, 4, 5, 7, 4, 5, 3, 9, 0};
    System.out.println(Arrays.toString(a));
    quickSort(a);
    System.out.println(Arrays.toString(a));
    Executors.newSingleThreadExecutor();
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
    //5, 对key左边的数快排
    quickSort(a, low, i - 1);
    //6, 对key右边的数快排
    quickSort(a, i + 1, high);
  }
}


class QuickSortCxs {


  public static void main(String[] args) {
    int[] a = {1, 2, 4, 5, 7, 4, 5, 3, 9, 0};

    quick(a, 0, a.length - 1);

//    System.out.println(Arrays.toString(a));
    sort(a);

  }

  static void quick(int[] arr, int low, int hight) {

    if (low > hight) {
      return;
    }

    int i = low;
    int j = hight;
    int key = arr[low];

    while (i < j) {
      // 从右往左找到第一个比 key 小的数
      while (i < j && arr[j] > key) {
        j--;
      }

      // 从左往右找到第一个比 key 大的数
      while (i < j && arr[i] <= key) {
        i++;
      }

      if (i < j) { // 交换位置
        int p = arr[i];
        arr[i] = arr[j];
        arr[j] = p;

      }
    }

    int p = arr[low];
    arr[low] = arr[i];
    arr[i] = p;

    quick(arr, low, i - 1);
    quick(arr, i + 1, hight);


  }

  public static void sort(int[] a) {

    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a.length; j++) {
        if (a[j] >= a[i]) {
          int p = a[i];
          a[i] = a[j];
          a[j] = p;
        }
      }
    }

    System.out.println(Arrays.toString(a));

  }
}

































