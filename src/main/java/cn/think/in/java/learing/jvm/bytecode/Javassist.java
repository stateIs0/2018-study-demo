package cn.think.in.java.learing.jvm.bytecode;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.NotFoundException;

/**
 // * @see https://github.com/jboss-javassist/javassist
 *
 * @author 玄灭
 * @date 2018/10/12-上午7:32
 */
public class Javassist {

    public static void main(String[] args)
        throws CannotCompileException, NotFoundException, IllegalAccessException, InstantiationException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass pt = pool.makeClass("TestBean2", pool.get("cn.think.in.java.learing.jvm.bytecode.TestBean"));

        pt.addConstructor(CtNewConstructor.defaultConstructor(pt));


        CtMethod method1 = new CtMethod(pool.get("java.lang.String"), "getM", null, pt);
//        CtNewMethod.make(
//            "public Object newInstance(" + String.class.getName() + " h){ return new " + "TestBean"
//                + "($1); }", pt);
//        pt.addMethod(CtNewMethod.make(
//            "public Object newInstance(" + String.class.getName() + " h){ return new " + "TestBean"
//                + "($1); }", pt));

        method1.setBody("{return \"你好\";}");
        pt.addMethod(method1);
        CtConstructor cc = new CtConstructor(null, pt);
        cc.setBody("this.field=\"why?\";");
        CtMethod method2 = new CtMethod(CtClass.voidType, "setF",
            new CtClass[]{pool.get("java.lang.String")}, pt);
        method2.setBody("{this.field=$1;}");
        pt.addMethod(method2);
        pt.writeFile();
        Class<?> c = pt.toClass();
        pt.writeFile();
        TestBean bean = (TestBean) c.newInstance();
        System.out.println(bean.getM());
        System.out.println(bean.getF());
        bean.setF("setf");
        System.out.println(bean.getF());


    }

}

