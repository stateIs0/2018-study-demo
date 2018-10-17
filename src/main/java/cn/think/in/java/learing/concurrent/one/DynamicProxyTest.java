package cn.think.in.java.learing.concurrent.one;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DynamicProxyTest implements InvocationHandler, UserService {

  private UserService userService;

  DynamicProxyTest(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    System.out.println("----------------------before-----------------------------------");
    Object result = method.invoke(userService, args);
    System.out.println("-----------------------after----------------------------------");
    return result;
  }

  public Object getProxy() {
    Object o =  Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
        userService.getClass().getInterfaces(), this);

    return o;
  }

  @Override
  public void function(String... args) {

  }
}

interface UserService {

  void function(String... args);
}

class UserServiceTest implements UserService {

  public void function(String... args) {
    for (String string : args) {
      System.out.println(string + "hello");
    }
  }

  public static <T> void main(String[] args) {
    Integer[] intArray = new Integer[10];
    Number[] numberArray = intArray;

    List<Integer> intList = new ArrayList<>();
    List<? extends Number> numberList = intList; //invalid
    List<? super Integer> numberList2 = intList; //invalid
    numberList2.add(1);
    // why
    //  numberList.add(new Float(3.3343232)); // fail

    List<?> list3 = new ArrayList<>();
    list3.add(null);
//    list3.add(new Object()); // can not add
    list3.get(0);  // return Object
    /**
     * why use ?
     *
     * public static boolean hasNulls(Pair<?> p) {
     return p.getFirst() == null || p.getSecond() == null;
     }
     */

    List<String> list1 = new ArrayList<>();
    list1.add("fffff");
    for (int i = 0; i < list1.size(); i++) {
      list1.get(i).charAt(1);

    }
    List<?> list2 = list1;
    System.out.println(list2.get(0));

  }
}

class Main {

  static Object generateProxy() {
    UserService proxy1 =  (UserService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
        UserServiceTest.class.getInterfaces(),
        (new DynamicProxyTest(new UserServiceTest())));

    UserService proxy2 = (UserService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
        UserServiceTest.class.getInterfaces(), new DynamicProxyTest(proxy1));

    return proxy2;

  }

  void noStaticFunction() {
  }

  public static void main(String[] args) {
//    UserService userService = new UserServiceTest();
//    DynamicProxyTest Test = new DynamicProxyTest(userService);

    UserService proxy = (UserService) generateProxy();
    proxy.function("caoxiansheng", "mengxinagren", "wuyuanyuan");
    System.exit(0);
    TwoTuple<String, Integer> t = new TwoTuple<String, Integer>(",", 1);
    String i = t.first + 1;
    System.out.println(t.first);
    new ThreeTuple<>("", 1, 1);


  }
}
