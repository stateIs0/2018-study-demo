package cn.think.in.java.learing.loader.asm.agent;

import cn.think.in.java.learing.loader.asm.Account;
import cn.think.in.java.learing.loader.asm.stat.time.TimeStatClassAdapter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

public class PreMainTraceAgent {

  public static void agentmain(String agentArgs, Instrumentation inst)
      throws UnmodifiableClassException {
    System.out.println("Agent Main called");
    System.out.println("agentArgs : " + agentArgs);
//    premain(agentArgs, inst);
    inst.addTransformer(new ClassFileTransformer() {

      @Override
      public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
          ProtectionDomain protectionDomain, byte[] classfileBuffer)
          throws IllegalClassFormatException {
//        System.out.println("premain load Class     :" + className);
//        return classfileBuffer;
        if (className.equals("cn/think/in/java/learing/loader/asm/Account")) {
          System.out.println("agent load Class " + className);
          ClassReader cr = new ClassReader(classfileBuffer);
          ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
          TimeStatClassAdapter classAdapter = new TimeStatClassAdapter(cw);
          cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
          return cw.toByteArray();
        } else {
          System.out.println(className);
          return classfileBuffer;
        }

      }
    }, true);
    inst.retransformClasses(Account.class);
  }

  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println("agentArgs : " + agentArgs);
    inst.addTransformer(new ClassFileTransformer() {

      @Override
      public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
          ProtectionDomain protectionDomain, byte[] classfileBuffer)
          throws IllegalClassFormatException {
        System.out.println("premain load Class     :" + className);
        return classfileBuffer;
//        if (className.equals("cn/think/in/java/clazz/loader/asm/Account")) {
//          System.out.println("meet " + className);
//          ClassReader cr = new ClassReader(classfileBuffer);
//          ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
//          TimeStatClassAdapter classAdapter = new TimeStatClassAdapter(cw);
//          cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
//          return cw.toByteArray();
//        } else {
//          System.out.println(className);
//          return classfileBuffer;
//        }

      }
    }, true);
  }




}
