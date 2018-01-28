package cn.think.in.java.jvm.memory.analyzer;

public class GCLocalVarTest {

  public void localVarGc1() {
    // 无法回收 [Full GC (System.gc())  8381K->6802K(15872K), 0.0035768 secs]
    byte[] a = new byte[6 * 1024 * 1024];
    System.gc();
  }

  public void localVarGc2() {
    byte[] a = new byte[6 * 1024 * 1024];
    // 回收 [Full GC (System.gc())  8381K->658K(15872K), 0.0024637 secs]
    a = null;
    System.gc();
  }

  public void localVarGc3() {
    {
      byte[] a = new byte[6 * 1024 * 1024];
    }
    // 无法回收 [Full GC (System.gc())  8381K->6802K(15872K), 0.0025098 secs]
    System.gc();
  }

  public void localVarGc4() {
    {
      byte[] a = new byte[6 * 1024 * 1024];
    }
    // 因为 c 复用了 a 的槽位，a 被销毁  成功回收  [Full GC (System.gc())  8381K->658K(15872K), 0.0023100 secs]
    int c = 10;
    System.gc();
  }

  public void localVarGc5() {
    // 调用 localVarGc1 后，栈帧被销毁，成功回收
    //[Full GC (System.gc())  8381K->6802K(15872K), 0.0025309 secs]
    //[Full GC (System.gc())  6802K->658K(16524K), 0.0014378 secs]
    localVarGc1();
    System.gc();
  }

  /**
   * 使用 -XX：+PrintGC 查看垃圾回收前后堆的大小。
   * @param args
   */
  public static void main(String[] args) {
    GCLocalVarTest gc = new GCLocalVarTest();
    gc.localVarGc5();
  }
}
