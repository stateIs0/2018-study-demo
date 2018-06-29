package cn.think.in.java.learing.jvm.memory.analyzer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * -XX:+PrintGCDetails -XX:PermSize=5m -XX:MaxPermSize=5m
 * 指定永久区（方法区）5m，最大永久区 5m，当耗尽时，内存溢出。
 *
 * 1.6，1.7 中，方法区可以理解为永久区（Perm）.永久区可以使用参数-XX:PermSize=5m -XX:MaxPermSize=5m 指定，默认情况下，
 * -XX:MaxPermSize 为 64M，一个大的永久区会保存更多的类信息，如果系统使用了一些动态代理，那么有可能会在运行时生成大量的类，
 * 导致永久区溢出。
 *
 * jdk 8 没有永久代的概念 ，使用 -XX:MaxMetaspaceSize=5m
 *
 * 永久代被彻底移除，取而代之的是元数据区，元数据区大小可以使用参数 -XX:MaxMetaspaceSize 指定（一个大的元数据区可以使系统支持更多的类），
 * 这是一块堆外的直接内存。与永久区不同，如果不指定大小，默认情况下，虚拟机会耗尽所有的可用系统内存。
 */
public class PermTest {

  public static void main(String[] args) {
    int i = 0;
    try {
      for (i = 0; i < 100000454; i++) {
        Object o = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
            new Class[]{Runnable.class},
            new InvocationHandler() {
              @Override
              public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return new Runnable() {
                  @Override
                  public void run() {
                    System.out.println("hello");
                  }
                };
              }
            });
      }

    } catch (Error e) {
      System.err.println("溢出 total create count : " + i);
    }
  }

  private interface A {

  }

}
