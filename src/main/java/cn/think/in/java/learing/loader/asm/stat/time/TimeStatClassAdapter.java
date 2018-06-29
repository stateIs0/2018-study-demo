package cn.think.in.java.learing.loader.asm.stat.time;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class TimeStatClassAdapter extends ClassVisitor {


  public TimeStatClassAdapter(ClassVisitor cv) {
    super(Opcodes.ASM5, cv);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc, String signature,
      String[] exceptions) {
    MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
    MethodVisitor wrapperMv = mv;
    if (mv != null) {
      if (name.equals("operation")) {
        wrapperMv = new TimeStatMethodAdapter(mv);
      }
    }
    return wrapperMv;
  }


}
