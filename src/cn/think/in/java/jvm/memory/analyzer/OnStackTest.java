package cn.think.in.java.jvm.memory.analyzer;

/**
 * User 实例需要 16 字节
 *
 * vm args ： -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB -XX:+EliminateAllocations
 *
 * 使用参数 -server 执行程序，因为在 Server 模式下，才可以启用逃逸分析。
 * 参数 -XX:+DoEscapeAnalysis 启用逃逸分析， -Xmx10m 指定了堆最大空间为10m。
 * 参数 -XX:+PrintGC 将打印GC日志
 * 参数 -XX:+EliminateAllocations 开启标量替换（默认打开），允许将对象打散分配在栈上，比如对象拥有id和name 两个字段，那么这两个字段将会被视为两个独立
 * 的局部变量进行匹配。
 * 参数 -XX:-UseTLAB 关闭 TLAB (堆中线程私有的分配缓存区)
 */
public class OnStackTest {

  public static class User{
    public int id ;
    public String name = "";
  }

  public static void  alloc(){
    User u = new User();
    u.id = 5;
    u.name = "cxs";
  }

  /**
   * 不开启栈上分配，GC 清理堆中内存
   *
     [GC (Allocation Failure)  2752K->660K(9920K), 0.0014042 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0006385 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0001099 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0001012 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0001103 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0000997 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0000831 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0000812 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0000766 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0000729 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0000876 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0001069 secs]
     [GC (Allocation Failure)  3412K->660K(9920K), 0.0001397 secs]
   * @param args
   */
  public static void main(String[] args) {
    long b = System.currentTimeMillis();
    for (int i = 0; i < 100000000; i++) {
      alloc();
    }
    long e = System.currentTimeMillis();
    System.out.println(e - b);
  }

  /**
   * 开启栈上分配 栈帧释放，无需GC 处理，速度很快
   * [GC (Allocation Failure)  2816K->698K(9984K), 0.0014242 secs]
      6
   */
}
