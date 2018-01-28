package cn.think.in.java.jvm.memory.analyzer;

/***
 * -Xss 越大 count 越大
 *
 * 256k 时7220
 * 128k 时2509
 *
 */
public class TestStackDeep {

  private static int count;

  public static void recursion() {
    count++;
    recursion();
  }

  public static void main(String[] args) {
    try {
      recursion();
    } catch (StackOverflowError e) {
      System.out.println("deep of calling = " + count);
    }
  }

}


/**
 * -Xss 128 deep = 695
 *
 * 局部变量表过大引起调用层次变浅
 *
 * 使用 jclasslib 工具查看函数的局部变量信息
 */
class TestStackDeep2 {

  private static int count;

  public static void recursion(long a, long b, long c) {
    long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
    count++;
    recursion(a, b, c);
  }

  public static void main(String[] args) {
    try {
      recursion(1,2,3);
    } catch (StackOverflowError e) {
      System.out.println("deep of calling = " + count);
    }
  }

}
