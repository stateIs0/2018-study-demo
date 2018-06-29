package cn.think.in.java.learing.concurrent.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList 的一个线程安全的变体，其中所有可变操作（add、set 等等）都是通过对底层数组进行一次新的复制来实现的。

 这一般需要很大的开销，但是当遍历操作的数量大大超过可变操作的数量时，这种方法可能比其他替代方法更 有效。
 在不能或不想进行同步遍历，但又需要从并发线程中排除冲突时，它也很有用。
 “快照”风格的迭代器方法在创建迭代器时使用了对数组状态的引用。此数组在迭代器的生存期内不会更改，因此不可能发生冲突，
 并且迭代器保证不会抛出 ConcurrentModificationException。创建迭代器以后，迭代器就不会反映列表的添加、移除或者更改。
 在迭代器上进行的元素更改操作（remove、set 和 add）不受支持。这些方法将抛出 UnsupportedOperationException。

 允许使用所有元素，包括 null。

 内存一致性效果：当存在其他并发 collection 时，将对象放入 CopyOnWriteArrayList 之前的线程中的操作 happen-before 随后通过另一线程从 CopyOnWriteArrayList 中访问或移除该元素的操作。

 */
public class CopyOnAWriteArrayListDemo {

  public static void main(String[] args) {
    CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
    list.add("");
//    list.get(1);
    list.remove(1);
//    list.remove("");
    Iterator iterator = list.iterator();

//    for (String s : list){
//      list.remove(s);
//    }
    ArrayList<String> list1 = new ArrayList();
    list1.iterator();
    for (int i = 0; i < 10; i++) {
      list1.add("");
    }

    for (String s : list1){
      list1.remove(s);

    }
    list1.add("");
//    list.get(1);
    list1.remove("");

    Vector vector = new Vector();
    vector.add("");
//    vector.get(1);
  }

}
