package cn.think.in.java.concurrent.one;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class MapPutTest extends WeakReference {


  public MapPutTest(Object referent) {
    super(referent);
  }

  public static void main(String[] args) {
    HashMap<String, Integer> hashMap = new HashMap<>(5);
    hashMap.put("one", 1);
    Integer one = hashMap.get("one");
    System.out.println(one);
  }

}
