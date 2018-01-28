package cn.think.in.java.jvm.memory.analyzer;


/**
 * 局部变量表是重要的垃圾回收根节点
 *
 * 只要被局部变量表中直接或间接引用的对象都是不会被回收的。
 */
public class TestStackSlot {

  public void loclaVar1(){
    int a = 0;
    System.out.println(a);
    int b = 0;
  }

  /**
   * a 和 b 的槽位会复用，因为a出了代码块就失效了
   */
  public void localVar2(){
    {
      int a = 0;
      System.out.println(a);
    }
    int b = 0;
  }

  public static void main(String[] args) {
    System.out.println("hello world");
  }


}
