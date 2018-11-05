package cn.think.in.java.learing.proxy;

import java.io.Serializable;

import org.springframework.cglib.core.DebuggingClassWriter;

/**
 *
 *
 * @author 玄灭
 * @date 2018/9/26-下午7:05
 */
public class NewCglibDemo implements Serializable {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./");

        Target t = Proxy.getProxy(new Target());
        t.hello();
        t.toString();


    }

}
