package cn.think.in.java.tools;

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
  }

}

class MyThreadLocal<T> extends ThreadLocal<T> {

  @Override
  protected T initialValue() {
    return (T) "world";
  }
}
