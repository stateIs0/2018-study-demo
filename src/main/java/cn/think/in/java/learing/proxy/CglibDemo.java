package cn.think.in.java.learing.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.cglib.core.DebuggingClassWriter;

/**
 *
 *
 * @author 玄灭
 * @date 2018/9/26-上午9:50
 */
public class CglibDemo {

    public static void main(String[] args)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./");
//        Target t = new Target();
//        Target p = Proxy.getProxy(t);
//        p.hello();
//        Target pp = Proxy.getProxy(p);
//        pp.hello();

        Target t = new Target();
        AdvisedSupport a = new AdvisedSupport();
        a.setTarget(t);
        Class c = Class.forName("org.springframework.aop.framework.CglibAopProxy");
        Constructor constructor = c.getDeclaredConstructor(AdvisedSupport.class);
        constructor.setAccessible(true);
        Object proxy = constructor.newInstance(a);

        Field f = c.getDeclaredField("advised");
        f.setAccessible(true);
        f.set(proxy, a);

        Method m = c.getDeclaredMethod("getProxy", ClassLoader.class);
        m.setAccessible(true);
        Object pObj = m.invoke(proxy, new Object[]{CglibDemo.class.getClassLoader()});

        ((Target) pObj).hello();

//        a.setTarget(pObj);
//
//        proxy = constructor.newInstance(a);
//
//       Object pObj2 = m.invoke(proxy, new Object[]{CglibDemo.class.getClassLoader()});

        Target target = Proxy.getProxy((Target) pObj);

        target.hello();

//        Target p = (Target) proxy.getProxy(CglibDemo.class.getClassLoader());
//        a.setTarget(p);
//        Target pp = (Target) proxy.getProxy(CglibDemo.class.getClassLoader());
//        pp.hello();
    }

}



