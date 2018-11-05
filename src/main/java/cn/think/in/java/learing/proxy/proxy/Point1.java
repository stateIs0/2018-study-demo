package cn.think.in.java.learing.proxy.proxy;

import java.lang.reflect.Method;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/4-下午9:29
 */
public class Point1 implements MethodPoint {

    @Override
    public boolean matches(Object obj, Method method, Object[] args) {
        return true;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) {
        System.out.println("point 1 before");
        Sleep.sleep(20);

        Object result = methodInvocation.proceed();

        Sleep.sleep(20);
        System.out.println("point 1 after");
        return result;
    }
}
