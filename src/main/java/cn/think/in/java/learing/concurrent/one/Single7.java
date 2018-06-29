package cn.think.in.java.learing.concurrent.one;

/**
 * 第七种方式：使用枚举
 *
 * 原理：枚举类型反编译之后可以看到实际上式要给继承自Enum的类。所以本质还是一个类。 因为枚举的特点，你只会有一个实例。
 */
public enum Single7 {

  // 实例
  SINGLE_7;

  /**
   * 获取实例
   */
  public Single7 getInstance() {
    return SINGLE_7;
  }

}

