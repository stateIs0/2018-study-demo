package cn.think.in.java.learing.jvm.bytecode;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/12-上午11:06
 */
public class JavassistDemo {

    public static void main(String[] args) {

    }

    void classTest() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("test.Rectangle");
            cc.setSuperclass(pool.get("test.Point"));
            cc.writeFile();

            //获取字节码
            byte[] b = cc.toBytecode();

            // 使用当前线程 classLoader 获取 class 对象
            Class clazz = cc.toClass();
            clazz = cc.toClass(Thread.currentThread().getContextClassLoader(), null);

            // 定义新的类
            pool.makeClass("className");

            // 如果一个 ctClass 对象调用了 writeFile, toClass,toBytecode 方法,就不能再修改.称之为 冻结
            // 但 调用 defrost 方法后, 又解冻.


        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }

}
