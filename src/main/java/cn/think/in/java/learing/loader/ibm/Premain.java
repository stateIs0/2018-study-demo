package cn.think.in.java.learing.loader.ibm;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class Premain {

  public static void premain(String agentArgs, Instrumentation inst)
      throws ClassNotFoundException, UnmodifiableClassException {
    inst.addTransformer(new Transformer());
  }
}
