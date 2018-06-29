package cn.think.in.java.learing.designPattern.builder;

public class ProductB extends Product{

  String nameB = "New B";

  @Override
  public String toString() {
    return "ProductB{" +
        "nameB='" + nameB + '\'' +
        ", name='" + name + '\'' +
        ", body='" + body + '\'' +
        '}';
  }
}
