package cn.think.in.java.learing.designPattern.builder;

/** 抽象创建者 */
public abstract class Builder {

  abstract Product getProduct();

  abstract void builderName();

  abstract void builderBody();
}
