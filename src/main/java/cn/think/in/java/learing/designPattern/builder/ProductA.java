package cn.think.in.java.learing.designPattern.builder;

public class ProductA extends Product{
  String nameA = "New A";

  @Override
  public String toString() {
    return "ProductB{" +
        "nameA='" + nameA + '\'' +
        ", name='" + name + '\'' +
        ", body='" + body + '\'' +
        '}';
  }
}
