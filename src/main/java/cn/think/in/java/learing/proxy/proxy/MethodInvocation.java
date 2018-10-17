package cn.think.in.java.learing.proxy.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 玄灭
 * @date 2018/10/4-下午9:26
 */
public class MethodInvocation {

    private List<MethodPoint> list = new ArrayList<>();
    private int index = -1;
    private Object target;

    private Method method;

    public MethodInvocation(List<MethodPoint> list, Object target, Method method) {

        for (MethodPoint methodPoint : list) {
            // 如果匹配
            if (methodPoint.matches(target, method, method.getTypeParameters())) {
                this.list.add(methodPoint);
            }
        }

        this.list = list;
        this.target = target;
        this.method = method;
    }




    public Object proceed() {

        Object result;
        if (++index == list.size()) {
            result = (target.toString());
            System.err.println("Target Method invoke result : " + result);
        } else {
            MethodPoint methodPoint = list.get(index);
            result = methodPoint.invoke(this);
        }
        return result;
    }




}
