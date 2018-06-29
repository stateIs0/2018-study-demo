package cn.think.in.java.learing.nio.io;

public interface Component {

  void method();
}

class ConcreteComponent implements Component {

  public void method() {
    System.out.println("原来的方法");
  }

}

abstract class Decorator implements Component {

  protected Component component;

  public Decorator(Component component) {
    super();
    this.component = component;
  }

  public void method() {
    component.method();
  }

}

class ConcreteDecoratorA extends Decorator{

  public ConcreteDecoratorA(Component component) {
    super(component);
  }

  public void methodA(){
    System.out.println("被装饰器A扩展的功能");
  }

  public void method(){
    System.out.println("针对该方法加一层A包装");
    super.method();
    System.out.println("A包装结束");
  }
}

class ConcreteDecoratorB extends Decorator{

  public ConcreteDecoratorB(Component component) {
    super(component);
  }

  public void methodB(){
    System.out.println("被装饰器B扩展的功能");
  }

  public void method(){
    System.out.println("针对该方法加一层B包装");
    super.method();
    System.out.println("B包装结束");
  }
}

class Demo{

  public static void main(String[] args) {
    Component c = new ConcreteComponent();
    c = new ConcreteDecoratorB(new ConcreteDecoratorA(c));
    c.method();


  }
}