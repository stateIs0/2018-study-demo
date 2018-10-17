package cn.think.in.java.learing.proxy;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/4-下午9:07
 */
public class MyFilter implements Filter {

    public Object invoke(FilterChain chain) {
        System.out.println("before 1");
        Object result = chain.invoke();
        System.out.println("after 1");
        return result;
    }

}

class MyFilter2 implements Filter{

    public Object invoke(FilterChain chain) {
        System.out.println("before 2");
        Object result =  chain.invoke();
        System.out.println("after 2");
        return result;
    }

}

interface Filter {

    Object invoke(FilterChain chain);
}