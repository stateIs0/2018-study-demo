package cn.think.in.java.lambda;


/**
 * 在Java 8中采用的是内部类来实现Lambda表达式
 *
 * javap -p Lambda.class 查看字节码文件
 *
 * lambda 表达式 -----即 匿名函数
 *
 * @param <T>
 */

/**
 * 函数式接口只能有一个抽象方法
 *
 * java 8 可以有 default 方法
 * @param <T>
 */
@FunctionalInterface// 即使不标注，也会认为是函数是接口，但如果不符合函数式接口，却使用这个注解，就会报错
interface Print<T> {

  void print(T x);

  /**
   * 如果一个类实现了多个接口，每个接口的都有同名的 default 方法，编译器要求重写该方法
   * @param object
   * @return
   */
  default boolean equalss(Object object){
    System.out.println();
    return true;
  }
}

public class Lambda {

  public static void PrintString(String s, Print<String> print) {
    print.print(s);
  }


  /**
   * 会生成这个方法，如果我们自定义了这个方法，就会冲突
   *
   * Error:(23, 23) java: 符号lambda$main$0(java.lang.String)与cn.think.in.java.lambda.Lambda中的 compiler-synthesized 符号冲突
   * Error:(1, 1) java: 符号lambda$main$0(java.lang.String)与cn.think.in.java.lambda.Lambda中的 compiler-synthesized 符号冲突
   * @param s
   */
//  private static void lambda$main$0(String s){
//  }


  /**
   * LambdaMetafactory.metafactory 会生成一个内部类
   *
   * -Djdk.internal.lambda.dumpProxyClasses 参数将内部类生成文件可以看出
   *
   * @param args
   */
  public static void main(String[] args) {
    PrintString("212121", (x) -> System.out.println(x));
  }
}

/*
package cn.think.in.java.lambda;

    import java.lang.invoke.LambdaForm.Hidden;

// $FF: synthetic class
final class Lambda$$Lambda$1 implements Print {
  private Lambda$$Lambda$1() {
  }

  @Hidden
  public void print(Object var1) {
    Lambda.lambda$main$0((String)var1);
  }
}

*/
