package cn.think.in.java.clazz.loader.ibm;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class AgentMain {

  public static void agentmain(String agentArgs, Instrumentation inst)
      throws ClassNotFoundException, UnmodifiableClassException,
      InterruptedException {
    inst.addTransformer(new Transformer(), true);
    inst.retransformClasses(TransClass.class);
    System.out.println("Agent Main Done");
  }



}
