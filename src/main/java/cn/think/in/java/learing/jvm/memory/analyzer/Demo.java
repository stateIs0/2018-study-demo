package cn.think.in.java.learing.jvm.memory.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class Demo {

  static class OOMObject{

  }

  public static void main(String[] args) {
    List<OOMObject> list = new ArrayList<>();
    for (;;){
      list.add(new OOMObject());
    }
  }

}


class A{

  public static void main(String[] args) {
    A a1 = new A();
    System.out.println(a1);
    A a2 = a1;
    System.out.println(a2);
    a1 = null;
    System.out.println(a1);
    System.out.println(a2);
  }
}
