package cn.think.in.java.learing.concurrent.lock;

public class Summarize {

  /**
   * 整理了 ReentrantLock 的常用方法。
   *
   * lock 获得锁，如果锁已经被占用，则等待
   * lockInterruptibly 获得锁，但优先响应中断
   * tryLock 尝试获得锁
   * tryLock（long, Timeunit） 尝试在给定的时间获得锁
   * unLock 释放锁
   */

  /**
   * 就重入锁的实现来看，它主要集中在Java 层面，在重入锁的实现种，主要包含三个要素：
   * 1. 原子状态。原子状态使用 CAS 操作，来存储当前锁的状态，判断锁是否已经被别的线程持有。
   * 2. 是等待队列。所有没有请求到锁的线程，会进入到等待队列进行等待。待有线程释放锁后，系统就能从等待队列种唤醒一个线程，继续工作。
   * 3. 是阻塞源于 park 和 unpark（） ，用来挂起和恢复线程。没有得到锁的线程将会被挂起。park 和 unpark {@linkplain java.util.concurrent.locks.LockSupport}
   */

}
