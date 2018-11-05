package cn.think.in.java.learing.proxy.proxy;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/4-下午9:38
 */
public class Test {

    public static void main(String[] args) {

        ProxyConfig proxyConfig = new ProxyConfig(new SayHello());

        Object proxy = ProxyFactory.create(proxyConfig).getProxy();

        proxy.toString();
    }


    static class SayHello {

        @Override
        public String toString() {
            return "hello cglib !";
        }
    }
}
