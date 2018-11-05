package cn.think.in.java.learing.concurrent.test;

import java.util.Arrays;

/**
 *
 *
 * @author 莫那·鲁道
 * @date 2018/11/5-下午3:48
 */
public class QsDemo {

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
        f(a, i + 1 , high);




    }

    public static void main(String[] args) {
        int[] a = {9, 1, 4, 2, 6, 8, 4, 1, 0};
        f(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }



}

class B {

    public static void main(String[] args) {
        B b = new B();
        new Thread(new Runnable() {
            @Override
            public void run() {
                b.print0();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                b.print1();
            }
        }).start();
    }


    synchronized void print0() {
        for (int i = 0; i <= 100 ; i+= 2) {
            System.out.println(i);
            notify();
            if (i >= 100) {
                return;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized void print1() {
        for (int i = 1; i <= 100 ; i+= 2) {
            System.out.println(i);
            if (i >= 100) {
                return;
            }
            notify();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
