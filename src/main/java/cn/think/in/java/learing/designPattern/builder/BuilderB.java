package cn.think.in.java.learing.designPattern.builder;

/** 具体创建者，实际场景中，会有多个实现，即一个产品实现对应一个构建者 */
public class BuilderB extends Builder {

  Product product = new ProductB();

  @Override
  Product getProduct() {
    return product;
  }

  /** 具体构建者需要知道如何构建对象 */
  @Override
  void builderName() {
    product.name = "Name B";
  }

  @Override
  void builderBody() {
    product.body = "Body B";
  }
}
