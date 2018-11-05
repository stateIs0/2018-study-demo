package cn.think.in.java.learing.concurrent.test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author 莫那·鲁道
 * @date 2018/11/5-下午4:11
 */
public class AbtDemo {

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
        int[] a = {4, 1, 5, 8, 2, 0, 9};

        f2(a);
//        f(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));

    }

    static void f2(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i] < a[j]) {
                    int p = a[i];
                    a[i] = a[j];
                    a[j] = p;
                }
            }
        }
    }


}

class A2 {

    static AtomicInteger num = new AtomicInteger();
    static volatile boolean flag = false;

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num.get() < 100) {
                    if (num.get() % 2 == 0) {
                        System.out.println(num.get());
                        num.incrementAndGet();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num.get() < 100) {
                    if (num.get() % 2 != 0) {
                        System.out.println(num.get());
                        num.incrementAndGet();
                    }
                }
            }
        }).start();
    }
}

class DeadLock {

    static DeadLock obj1 = new DeadLock();
    static DeadLock obj2 = new DeadLock();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj1.a();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj2.b();
            }
        }).start();
    }

    synchronized void a() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        obj2.b();
    }

    synchronized void b() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        obj1.a();
    }
}












































