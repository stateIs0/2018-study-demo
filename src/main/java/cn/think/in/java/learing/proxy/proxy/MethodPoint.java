package cn.think.in.java.learing.proxy.proxy;

import java.lang.reflect.Method;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/7-上午11:40
 */
public interface MethodPoint {

    boolean matches(Object obj, Method method, Object[] args);

    Object invoke(MethodInvocation methodInvocation);
}