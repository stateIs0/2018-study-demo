package cn.think.in.java.clazz.loader.asm;

import cn.think.in.java.clazz.loader.asm.stat.time.TimeStatClassAdapter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

public class PreMainAddTimeStatAgent {

  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println("agentArgs : " + agentArgs);
    inst.addTransformer(new ClassFileTransformer() {

      @Override
      public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
          ProtectionDomain protectionDomain, byte[] classfileBuffer)
          throws IllegalClassFormatException {

        if (className.equals("")) {
          System.out.println("meet " + className);
          ClassReader cr = new ClassReader(classfileBuffer);
          ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
          TimeStatClassAdapter classAdapter = new TimeStatClassAdapter(cw);
        } else {
          System.out.println(className);
          return classfileBuffer;
        }

        return new byte[0];
      }
    });
  }

}

