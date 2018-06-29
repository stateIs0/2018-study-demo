package cn.think.in.java.learing.designPattern.builder;


/** 测试客户端 */
public class BuilderClient {

  public static void main(String[] args) {
    Builder a = new BuilderA();
    Builder b = new BuilderB();

    Director director = new Director();
    director.createProduct(a);
    director.createProduct(b);

    System.out.println(a.getProduct());
    System.out.println(b.getProduct());


  }
}

interface A{

  void a();
}
