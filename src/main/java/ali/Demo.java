package ali;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

public class Demo {


    public static void main1(String[] args) throws FileNotFoundException {
        sort(null, 1, 1);
    }

    public static void sort(int[] a, int low, int high) throws FileNotFoundException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(
            (Runnable) null);

    }

    public static void main(String[] args) throws IOException {

        while (true) {
            char c = (char) System.in.read();
            if (c == 'a') {
                fun4();

            }
        }

    }


    static void fun4() {
        fun3();
    }

    static void fun3() {

        fun2();
    }

    static void fun2() {
        fun1();
    }

    static void fun1() {
        try {
            fun();
            fun0();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void fun0() {
        System.out.println("hello");
    }

    private static void fun() throws InterruptedException {
        List<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            integers.add(i);
            Thread.sleep(10);
        }

        integers.parallelStream().reduce(Integer.MIN_VALUE, (a, b) ->
            Integer.max(a, b)
        );
        System.out.println("over");
    }

}
