package cn.think.in.java.learing.se;

public class CloneableTest {

  /*
  基本类型直接拷贝值
  引用类型拷贝引用
  String 类型拷贝引用。但如果修改了 String，那么将会新建一个 String 对象。也就是 String 类型是可以深拷贝的，修改 a 对象不会影响 b 对象。
   */
  public static void main(String[] args) throws CloneNotSupportedException {
//    Man m1 = new Man("tom", new Computer("win"));
//    System.out.println(m1);
//    Man m2 = (Man) m1.clone();
//    System.out.println(m2);

    /*
    Man{name='tom', computer=Computer{osType='win'}951007336}2001049719
    Man{name='tom', computer=Computer{osType='win'}951007336}1528902577
     */

    Man m1 = new Man("tom", new Computer("win"));
    m1 = new Man("tom", new Computer("win"));
    System.out.println(m1);
    Man m2 = (Man) m1.clone();
    System.out.println(m2);

    m1.computer.osType = "Mac";

    System.out.println(m1);
    System.out.println(m2);
  }


}


final class Man implements Cloneable {

  String name;
  Computer computer;

  public Man(String name, Computer computer) {
    this.name = name;
    this.computer = computer;
  }

  @Override
  public String toString() {
    return "Man{" +
        "name='" + name + '\'' +
        ", computer=" + computer +
        '}' + hashCode();
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
//    return super.clone();
    Man man = new Man(this.name, new Computer(this.computer.osType));
    return man;
    /*
    Man{name='tom', computer=Computer{osType='win'}951007336}2001049719
    Man{name='tom', computer=Computer{osType='win'}1528902577}1927950199
     */

  }

}

class Computer {

  String osType;

  public Computer(String osType) {
    this.osType = osType;
  }

  @Override
  public String toString() {
    return "Computer{" +
        "osType='" + osType + '\'' +
        '}' + hashCode();
  }
}
