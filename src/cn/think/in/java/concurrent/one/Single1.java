package cn.think.in.java.concurrent.one;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * 懒汉式加载：最简单的单例模式：2步，
 * 1.把自己的构造方法设置为私有的，不让别人访问你的实例，
 * 2.提供一个static方法给别人获取你的实例
 */
public class Single1 {

  // 一个静态的实例
  private static Single1 single;

  // 私有的构造方法
  private Single1() {
  }

  public static Single1 getInstance() {
    // 如果为null，说明没有初始化
    if (single == null) {
      // 初始化实例
      single = new Single1();
    }
    // 返回实例
    return single;
  }
}


/**
 * 饿汉式加载
 */
class Single2 {

  // 类加载时就实例化对象
  private static final Single2 single2 = new Single2();

  public static Single2 getInstance() {
    // 立即返回
    return single2;
  }

}

/**
 * 测试用例
 */
class Test11 {

  /**
   * 并发引起现有的单例模式失败
   */
  public static void main(String[] args) throws InterruptedException {
    // 使用Set去重
    Set<Single1> set = new HashSet<>();
    // windows 每个进程最多1000个线程
    for (int i = 0; i < 1000; i++) {
      // 并发获取我们的实例
      new Thread(() -> {
        // 向set添加实例
        set.add(Single1.getInstance());
      }).start();
    }
    // 等待10秒
    Thread.sleep(10000);
    System.out.println("-----我们的Single单例模式测试-------");
    // 循环打印实例，小几率会出现2个实例或多个实例
    for (Single1 single : set) {
      System.out.println(single);
    }
  }

}


/**
 * 第三种方式：无脑同步式，效率极低
 */
class Single3 {

  private static Single3 single3;

  private Single3() {
  }

  /**
   * 同步静态方法，防止并发导致的出现2个实例,但是还是能优化
   */
  public synchronized static Single3 getInstance() {
    if (single3 == null) {
      single3 = new Single3();
    }
    return single3;
  }
}


/**
 * 第四种：双重检验锁。
 */
class Single4 {

  private static volatile Single4 single4;

  private Single4() {
  }

  /**
   * 双重检查获取单例实例
   *
   */
  public static Single4 getInstance() {
    // 多线程直接访问，不做控制，不影响性能
    if (single4 == null) {
      // 此时，如果有多个线程进入，则进入同步块，其余线程等待，
      synchronized (Single4.class) {
        // 此时，第一个进入的线程判断为null，但第二个线程进来时已经不是null了
        if (single4 == null) {
          // 第一个线程实例化此对象
          single4 = new Single4();
        }

      }
    }
    // 如果不为null，不会影响性能，只有第一次才会影响性能
    return single4;
  }
}

/**
 * 第五种方法：既要懒汉式加载，又要线程安全：静态内部类。
 *
 */
class Single5 {

  private static Single5 single5;

  private Single5() {
  }

  public static Single5 getInstance() {
    return InnerClass.single5;
  }

  /**
   * 使用静态内部类，既能保证懒加载，又能保证线程安全
   */
  private static class InnerClass {

    private static final Single5 single5 = new Single5();
  }

}


/**
 * 第6种方法：反射和反序列号
 */
class Single6 {

  private static Single6 single5;

  private Single6() {
  }

  public static Single6 getInstance() {
    return Single6.InnerClass.single6;
  }

  private static class InnerClass {

    private static final Single6 single6 = new Single6();
  }

  /**
   * 重写此方法，防止反序列号破坏单例机制，这是因为：反序列号的机制在反序列号的时候，会判断如果实现了serializable或者
   * externalizable接口的类中包含readResolve方法的话，会直接调用readResolve方法来获取实例。
   */
  public Object readResolve() {
    return InnerClass.single6;
  }


  public static void main(String[] args)
      throws IllegalAccessException, InstantiationException, IOException, ClassNotFoundException {
    // 反射获取实例
    Single6 single6 = Single6.class.newInstance();
    Single6 single6two = getInstance();
    System.out.println(single6 + "\n" + single6two);

    String filePath = "";
    // 反序列号获取实例
    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));

    // 将实例写入到文件种
    outputStream.writeObject(getInstance());
    // 从文件种读取出来，成为新的实例
    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
    Single6 single = (Single6) inputStream.readObject();

    outputStream.close();
    // 但是这种方法可以通过重写readResolve方法来防止，

  }
}





































































































