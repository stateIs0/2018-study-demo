package cn.think.in.java.learing.proxy;

/**
 *
 *
 * @author 玄灭
 * @date 2018/9/26-下午7:05
 */
public class Target {

    Target t ;

//    public java.lang.Object newInstance(org.springframework.cglib.proxy.Callback c) {
//        return null;
//    }

    public Target() {
    }

    public Target(Target t) {
        this.t = t;
    }

    void hello() {
        System.out.println("hello targe!");
    }
}


