package cn.think.in.java.learing.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.core.NamingPolicy;
import org.springframework.cglib.core.Predicate;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 *
 *
 * @author 玄灭
 * @date 2018/9/26-下午7:05
 */
public class Proxy {

    private static final Enhancer en = new Enhancer();

    final static String STRESS_HASH_CODE = "";

    public void hello() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Proxy p = getProxy(new Proxy());
        p = getProxy(p);
        p.hello();
    }


    static <T> T getProxy(T origin) {
        en.setSuperclass(origin.getClass());
        en.setNamingPolicy(new Name());
        en.setCallback(new Invoker(origin));
        return (T) en.create();
    }

    private static class Invoker implements MethodInterceptor{

        Object origin;

        public Invoker(Object origin) {
            this.origin = origin;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
            return method.invoke(origin, objects);
        }
    }

    private static class Name implements NamingPolicy {

        @Override
        public String getClassName(String prefix, String source, Object key, Predicate names) {
            String base =
                "" + prefix + "$$" + source.substring(source.lastIndexOf(46) + 1)
                    + "$ByTedisCgLIB"
                    + "$$" + Integer.toHexString(key.hashCode());
            String attempt = base;
            return attempt;
        }
    }

    static class Target {


        @Override
        public String toString() {
            return "hello";
        }
    }
}
