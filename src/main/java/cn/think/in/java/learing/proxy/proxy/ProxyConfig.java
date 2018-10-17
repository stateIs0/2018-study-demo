package cn.think.in.java.learing.proxy.proxy;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/7-上午11:48
 */
public class ProxyConfig {

    private Object target;

    private boolean jdkProxy;

    private ClassLoader classLoader;

    private Class[] interfaces;

    public Class[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class[] interfaces) {
        this.interfaces = interfaces;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public boolean isJdkProxy() {
        return jdkProxy;
    }

    public void setJdkProxy(boolean jdkProxy) {
        this.jdkProxy = jdkProxy;
    }

    public ProxyConfig(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
