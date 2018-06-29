package cn.think.in.java.learing.concurrent.callable;

import java.util.ArrayList;
import java.util.List;

public class OrderClassLoader extends ClassLoader {

  private String fileName;

  private byte[] b = new byte[1024];

  public OrderClassLoader(String fileName) {
    this.fileName = fileName;
  }

  @Override
  protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
    Class re = findClass(name);
    if (re == null) {
      System.out.println("不能加载这个类 ：" + name);
      return super.loadClass(name, resolve);
    }
    return re;

  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    Class clazz = this.findLoadedClass(name);
    return super.findClass(name);
  }

  volatile static InheritableThreadLocal<CallableDemo> local = new InheritableThreadLocal<>();

  public static void mafin(String[] args) throws ClassNotFoundException {
    Class.forName("java.lang.String");
    String.class.getClassLoader().loadClass("java.lang.String");


  }

  public static void main(String[] args) {
    try {
      List list = new ArrayList();
      for (; ; ) {
        for (int i = 0; i < 21100; i++) {
          list.add(new OrderClassLoader(""));
        }
        System.gc();

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
            System.out.println("hook")
        ));
        Thread.sleep(2000);
      }
    } catch (OutOfMemoryError error) {
      error.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void finalize() throws Throwable {
    System.err.println("GC");
    super.finalize();
  }
}
