package cn.think.in.java.learing.loader.test;

/**
 *
 * @author 莫那·鲁道
 * @date 2018/10/21-下午3:36
 */
public class Son{

    public static void main(String[] args) {
//        Father[] father = new Father[1];
        Father father = new Father();
        new Son().hello();

    }

    public void hello() {
        System.out.println("hello son");
    }
}

class Father{

    public void hello() {
        System.out.println("hello father");
    }

}