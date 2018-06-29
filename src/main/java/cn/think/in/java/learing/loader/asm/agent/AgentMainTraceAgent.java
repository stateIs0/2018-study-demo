package cn.think.in.java.learing.loader.asm.agent;

import cn.think.in.java.learing.loader.asm.Account;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

public class AgentMainTraceAgent {


  public static void agentmain(String agentArgs, Instrumentation inst)
      throws UnmodifiableClassException {
    System.out.println("Agent Main called");
    System.out.println("agentArgs : " + agentArgs);
    inst.addTransformer(new ClassFileTransformer() {

      @Override
      public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
          ProtectionDomain protectionDomain, byte[] classfileBuffer)
          throws IllegalClassFormatException {
        System.out.println("agentmain load Class  :" + className);
        return classfileBuffer;
      }
    }, true);
    inst.retransformClasses(Account.class);
  }
}
