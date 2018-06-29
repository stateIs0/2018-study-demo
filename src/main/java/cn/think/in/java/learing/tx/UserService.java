package cn.think.in.java.learing.tx;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public interface UserService {

  /**
   * 可以事务1
   */
  void txMethod1();

  /**
   * 无事务方法
   */
  void noTxMethod();

  /**
   * 可以事务2
   */
  void txMethod2();

}

/**
 * 在Spring事务中，我们往往是在Service层进行事务控制。
 *
 * 我们在UserServiceImpl中想模拟的是：
 *
 * 一个有事务的方法，去调用另一个有事务的方法，会怎么样？
 *
 * 一个没有事务的方法，去调用一个有事务的方法，会怎么样？
 */
class UserServiceImpl implements UserService {


  @Override
  public void txMethod1() {
    // 里面的事务无效
    txMethod2();
  }

  @Override
  public void noTxMethod() {
    // 这种情况事务无效
    txMethod2();
  }

  @Override
  public void txMethod2() {

  }
}


class UserHandler implements InvocationHandler {

  UserService userService;

  public UserHandler(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    if (method.getName().startsWith("txMethod")) {
      before();
    }
    method.invoke(userService, null);

    if (method.getName().startsWith("txMethod")) {
      after();
    }
    return null;
  }

  public void before() {
    System.out.println("开启事务");
  }

  public void after() {
    System.out.println("提交事务");
  }
}


class UserTest{

  public static void main(String[] args) {
    UserService service = new UserServiceImpl();
    UserHandler handler = new UserHandler(service);

    UserService proxy = (UserService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
        new Class[]{UserService.class}, handler);

    proxy.txMethod2();

    System.out.println("====================");

    proxy.txMethod1();

    System.out.println("=====================");

    proxy.noTxMethod();

    System.out.println("===========================");

  }
}
