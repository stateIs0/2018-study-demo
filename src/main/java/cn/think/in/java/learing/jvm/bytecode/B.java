package cn.think.in.java.learing.jvm.bytecode;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

/**
 *
 *
 * @author 玄灭
 * @date 2018/10/12-上午7:48
 */
public class B {
    public   static   void  main(String[] args) {
        A a = new  A();
        a.method();
    }
}


class T2 {
    public   static   void  main(String[] args)  throws  Exception {
        //用于取得字节码类，必须在当前的classpath中，使用全称
        CtClass ctClass = ClassPool.getDefault().get("cn.think.in.java.learing.jvm.bytecode.A" );
        //需要修改的方法名称
        String mname = "method" ;
        CtMethod mold = ctClass.getDeclaredMethod(mname);
        //修改原有的方法名称
        String nname = mname + "$impl" ;
        mold.setName(nname);
        //创建新的方法，复制原来的方法
        CtMethod mnew = CtNewMethod.copy(mold, mname, ctClass, null );
        //主要的注入代码
        StringBuffer body = new  StringBuffer();
        body.append("{\nlong start = System.currentTimeMillis();\n" );
        //调用原有代码，类似于method();($$)表示所有的参数
        body.append(nname + "($$);\n" );
        body.append("System.out.println(\"Call to method "
            + mname
            + " took \" +\n (System.currentTimeMillis()-start) + "
            + "\" ms.\");\n" );

        body.append("}" );
        //替换新方法
        mnew.setBody(body.toString());
        //增加新方法
        ctClass.addMethod(mnew);
        //类已经更改，注意不能使用A a=new A();，因为在同一个classloader中，不允许装载同一个类两次
        A a=(A)ctClass.toClass().newInstance();
        A a2 = new A();
        a.method();
        ctClass.writeFile();
    }
}