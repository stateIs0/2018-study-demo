package cn.think.in.java.learing.concurrent.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author 莫那·鲁道
 * @date 2018/11/5-下午5:39
 */
public class MyQueue {

    ReentrantLock lock = new ReentrantLock();
    Condition noFull = lock.newCondition();
    Condition noEmpty = lock.newCondition();

    Object[] queue = new Object[1024];
    int putFlag = 0;
    int takeFlag = 0;
    int count = 0;

    public void put(Object x) throws InterruptedException {
        try {
            lock.lock();
            if (count == queue.length) {
                noFull.await();
            }

            queue[putFlag] = x;
            if (++putFlag == queue.length) {
                putFlag = 0;
            }

            count++;

            noEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        try {
            lock.lock();

            if (count == 0) {
                noEmpty.await();
            }

            Object result = queue[takeFlag];

            if (++takeFlag == queue.length) {
                takeFlag = 0;
            }

            count--;
            noFull.signal();

            return result;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (; ; ) {
                    try {
                        queue.put("hello" + ++i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        System.out.println(queue.take());
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

class B {

    public static void main(String[] args) {
        String a = "1234567890";

        char[] arr = a.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }
}
