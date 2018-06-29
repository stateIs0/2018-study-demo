package cn.think.in.java.learing.jvm.gc;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import sun.misc.Unsafe;

public class DirectDemo {

  /**
   * -Xmx2048m
   * -XX:MaxDirectMemorySize=2048m
   * -XX:MaxDirectMemorySize=4048m
   * -XX:+SerialGC
   * @param args
   * @throws IllegalAccessException
   * @throws InterruptedException
   */
  public static void main(String[] args) throws IllegalAccessException, InterruptedException {
    Field field = Unsafe.class.getDeclaredFields()[0];
    field.setAccessible(true);
    Unsafe unsafe = (Unsafe) field.get(null);
    int i = 0;
    long[] longs = new long[60];
    for (; ; ) {
//      longs[i] = unsafe.allocateMemory(100L * 1024 * 1024);
      ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 100);
      buffer = null;// 需要使用 Full GC 否则内存泄漏
      if (i++ == 59) {
        Thread.sleep(2000);
        for (int j = 0; j < longs.length; j++) {
          unsafe.freeMemory(longs[j]);
        }
        Thread.sleep(1000000);
      }
    }
  }

}
