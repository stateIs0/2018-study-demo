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
 * @date 2018/10/12-上午10:49
 */
public class Demo {

    public static void main(String[] args) throws IOException, CannotCompileException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("cn.think.in.java.learing.jvm.bytecode.A");
        cc.setSuperclass(pool.get("cn.think.in.java.learing.jvm.bytecode.Demo"));
        cc.writeFile();
    }

}
