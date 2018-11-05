package cn.think.in.java.learing.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/4-下午9:06
 */
public class FilterChain {

    private List<Filter> list;
    private Object target;

    private int index = -1;

    public FilterChain(Object target) {
        this.target = target;
        list = new ArrayList<>();
        list.add(new MyFilter());
        list.add(new MyFilter2());
    }

    public Object invoke() {

        Object result;
        if (++index == list.size()) {
            result = (target.toString());
            System.out.println(result);
        } else {
            Filter  filter = list.get(index);
            result = filter.invoke(this);
        }

        return result;
    }

}
