package cn.think.in.java.learing.jvm.gc;

import java.util.UUID;

public class StringInternTest {

  /**
   * java -verbose:gc -XX:+PrintGC -XX:+UseConcMarkSweepGC -XX:+UseParNewGC  -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:+PrintStringTableStatistics -Xmx1g -Xms1g -Xmn64m StringInternTest
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      UUID.randomUUID().toString().intern();
      int a = i;
      new CharSequence() {
        @Override
        public int length() {
          return a;
        }

        @Override
        public char charAt(int index) {
          return (char) a;
        }

        @Override
        public CharSequence subSequence(int start, int end) {
          return null;
        }

        @Override
        public String toString() {
          return a + "";
        }
      };
      Thread.sleep(20);
      if (i >= 100000 && i % 100000 == 0) {
        System.out.println("i=" + i);
      }
    }
  }
}
