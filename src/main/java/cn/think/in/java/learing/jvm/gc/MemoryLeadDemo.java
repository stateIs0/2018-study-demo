package cn.think.in.java.learing.jvm.gc;

import java.util.HashMap;
import java.util.Map;

public class MemoryLeadDemo {

  static class Key{
    Integer id;

    public Key(Integer id) {
      this.id = id;
    }

    @Override
    public int hashCode() {
      return id != null ? id.hashCode() : 0;
    }
  }

  public static void m1ain(String[] args) {
    Map map = new HashMap();
    for (;;){
      for (int i = 0; i < 10000; i++) {
        if (!map.containsKey(new Key(i))) {
          map.put(new Key(i), "Number : " + i);
        }
      }
    }
  }

  public static void mai1n(String[] args) {
    StringBuilder sb = new StringBuilder("");
    StringBuilder sb2 = sb;
    StringBuilder sb3 = sb;
    System.out.println(sb3 == sb2);
  }

  public static void main(String[] args) {

    String s = new String("9111");
    s.intern();
    String s2 = "9111";
    System.out.println( s == s2); // false

    String s3 = new String("1") + new String("1");
    s3.intern();// 常量池中保存的是s3的引用
    String s4 = "11";
    System.out.println(s3 == s4); // true
  }


}
