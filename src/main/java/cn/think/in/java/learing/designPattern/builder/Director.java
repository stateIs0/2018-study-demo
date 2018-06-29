package cn.think.in.java.learing.designPattern.builder;

/** 指挥者 */
public class Director {

  /** 此方法可以固定构建对象的过程，只需要传递具体构建者即可 */
  void createProduct(Builder builder) {
    builder.builderName();
    builder.builderBody();
  }

}
