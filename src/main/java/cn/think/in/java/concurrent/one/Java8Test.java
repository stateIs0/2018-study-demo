package cn.think.in.java.concurrent.one;

public class Java8Test {

  public static void main(String[] args) {
    new A1().a();
  }
}


@FunctionalInterface
interface A {

  void a();

  default void test(){
    System.out.println("hello world");
  }

  default void test2(Object o){
    System.out.println("hello world");
    ((A)o).test();
  }
}
@FunctionalInterface
interface B{
  void a();

  default void test(){
    System.out.println("b Test");
  }
}


class A1 implements A, B{

  @Override
  public void a() {
    System.out.println("a");
  }

  @Override
  public void test() {

  }

}
