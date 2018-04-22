package cn.think.in.java.concurrent.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * JDK7提供了7个阻塞队列。分别是

 {@link java.util.concurrent.ArrayBlockingQueue} ：一个由数组结构组成的有界阻塞队列。

 {@link java.util.concurrent.LinkedBlockingQueue} ：一个由链表结构组成的有界阻塞队列。

 {@link java.util.concurrent.PriorityBlockingQueue }：一个支持优先级排序的无界阻塞队列。

 {@link java.util.concurrent.DelayQueue}：一个使用优先级队列实现的无界阻塞队列。

 {@link java.util.concurrent.SynchronousQueue}：一个不存储元素的阻塞队列。

 SynchronousQueue是一个不存储元素的阻塞队列。每一个put操作必须等待一个take操作，否则不能继续添加元素。
 SynchronousQueue可以看成是一个传球手，负责把生产者线程处理的数据直接传递给消费者线程。队列本身并不存储任何元素
 ，非常适合于传递性场景,比如在一个线程中使用的数据，传递给另外一个线程使用，SynchronousQueue的吞吐量高于LinkedBlockingQueue 和 ArrayBlockingQueueDemo


 {@link java.util.concurrent.LinkedTransferQueue}：一个由链表结构组成的无界阻塞队列。

 {@link java.util.concurrent.LinkedBlockingDeque}：一个由链表结构组成的双向阻塞队列

 {@link java.util.concurrent.locks.AbstractQueuedSynchronizer}

 */
public class BlockingQueueDemo {

  public static void smain(String[] args) throws InterruptedException {
    LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(1);
    linkedBlockingQueue.put("");
//    linkedBlockingQueue.take();

    // 如果队列为空，直接返回null
    Object o3 = linkedBlockingQueue.poll();
    // 如果队列为空，一直阻塞
    Object o1 = linkedBlockingQueue.poll(1000, TimeUnit.MILLISECONDS);
    // 阻塞，直到取出数据
    Object o = linkedBlockingQueue.take();
    // 获取但不移除此队列的头；如果此队列为空，则返回 null。
    Object peek = linkedBlockingQueue.peek();

    // 如果满了，立即返回false
    boolean b = linkedBlockingQueue.offer("");
    // 如果满了，则等待，如果还满，则返回false
    boolean b2 = linkedBlockingQueue.offer("", 1000, TimeUnit.MILLISECONDS);
    // 阻塞直到插入为止
    linkedBlockingQueue.put("");

  }

  void a() throws InterruptedException {
    ArrayBlockingQueue queue = new ArrayBlockingQueue(1, false);

    queue.offer("");
    queue.offer("", 1, TimeUnit.MILLISECONDS);
    queue.put("");

    queue.poll();
    // 获取但不移除此队列的头；如果此队列为空，则返回 null。
    queue.peek();
    queue.take();


  }

  void aa() throws InterruptedException {
    SynchronousQueue queue = new SynchronousQueue(false);

    queue.offer("");
    queue.offer("", 1, TimeUnit.MILLISECONDS);
    queue.put("");

    queue.poll();
    // 获取但不移除此队列的头；如果此队列为空，则返回 null。
    queue.peek();
    queue.take();

  }


  public static void main(String[] args) {
    LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(1024);
    for (int i = 0; i < 5; i++) {
      final int num = i;
      new Thread(() -> {
        try {
          for (int j = 0; ; j++) {
            linkedBlockingQueue.put(num + "号线程的" + j + "号商品");
            Thread.sleep(5000);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }

    for (int i = 0; i < 5; i++) {
      new Thread(() -> {
        try {
          for (; ; ) {
            System.out.println("消费了" + linkedBlockingQueue.take());
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }

}


