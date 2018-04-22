package cn.think.in.java.concurrent.pool;

import java.lang.reflect.InvocationTargetException;

public class ThreadLocalDemo {

  public static void main(String[] args) {
    ThreadLocal<String> local = new MyThreadLocal<>();

    ThreadLocal<String> local2 = new MyThreadLocal<>();
    System.out.println(local.get());
    local.set(null);
    System.out.println(local.get());

    System.out.println("");

    local2.set("hello2");
    System.out.println(local.get());
    local.remove();
    System.out.println(local.get());
    System.out.println();
  }

  private ThreadLocalDemo() {
  }

}

class MyThreadLocal<T> extends ThreadLocal<T> {

  @Override
  protected T initialValue() {
    return (T) "world";
  }
}

class  BBB {

  public static void main(String[] args) {
    System.out.println(16 >>> 2);
  }
}




class BB {

  public static void ma1in(String[] args)
      throws IllegalAccessException, InstantiationException, InvocationTargetException {
//    AAA.class.newInstance();
//
//    Constructor<?>[] constructors = EnumDefault.class.getDeclaredConstructors();
//    Constructor constructors1 = constructors[0];
//    constructors1.setAccessible(true);
//    constructors1.newInstance(null);
//    System.out.println("");
//    for (Constructor constructor)

  }

  public static void main(String[] args) {
    System.out.println("hello");
  }

}
