package cn.think.in.java.learing.proxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/4-下午9:33
 */
public class ProxyFactory implements InvocationHandler {

    private ProxyConfig proxyConfig;
    List<MethodPoint> list = new ArrayList<>();

    private ProxyFactory(ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
    }

    public static ProxyFactory create(ProxyConfig proxyConfig) {
        return new ProxyFactory(proxyConfig);
    }

    public Object getProxy() {
        return getProxy(proxyConfig.getTarget());
    }

    public Object getProxy(Object origin) {

        if (proxyConfig.isJdkProxy()) {

            return Proxy
                .newProxyInstance(proxyConfig.getClassLoader(), proxyConfig.getInterfaces(), this);
        }

        final Enhancer en = new Enhancer();
        en.setSuperclass(origin.getClass());


        list.add(new Point1());
        list.add(new Point2());


        en.setCallback(new InterceptorLast());
        return en.create();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        return methodInvocation.proceed();
        MethodInvocation mi = new MethodInvocation(list, proxyConfig.getTarget(), method);
        return mi.proceed();
    }

    private class InterceptorLast
        implements MethodInterceptor {



        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
            return invoke(o, method, objects);
        }

    }


}
