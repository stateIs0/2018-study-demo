package cn.think.in.java.learing.concurrent.test;

import java.util.Arrays;

/**
 *
 * @author 莫那·鲁道
 * @date 2018/11/5-上午9:29
 */
public class QSTest {

    static void f(int[] a, int low, int high) {
        if (low > high) {
            return;
        }

        int i = low;
        int j = high;
        int key = a[low];
        while (i < j) {
            while (i < j && a[j] > key) {
                j--;
            }
            while (i < j && a[i] <= key) {
                i++;
            }
            if (i < j) {
                int p = a[i];
                a[i] = a[j];
                a[j] = p;
            }
        }

        int p = a[i];
        a[i] = a[low];
        a[low] = p;

        f(a, low, i - 1);
        f(a, i + 1, high);
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 1, 12, 5, 3, 0, 4};
        f(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }


}

class A23 {

    static void function(int[] a, int low, int high) {
        if (low > high) {
            return;
        }

        int i = low;
        int j = high;
        int key = a[low];
        while (i < j) {
            while (i < j && a[j] > key) {
                j--;
            }
            while (i < j && a[i] <= key) {
                i++;
            }
            if (i < j) {
                int p = a[i];
                a[i] = a[j];
                a[j] = p;
            }
        }

        int p = a[i];
        a[i] = a[low];
        a[low] = p;

        function(a, low, i - 1);
        function(a, i + 1, high);
    }

    public static void main(String[] args) {
        int[] a = {9, 8, 2, 1, 34, 56, 1, 0, 5};
        function(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));

    }
}
