package cn.think.in.java.learing.loader.asm.stat.time;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class TimeStatMethodAdapter extends MethodVisitor implements Opcodes {

  public TimeStatMethodAdapter(MethodVisitor methodVisitor) {
    super(Opcodes.ASM5, methodVisitor);
  }

  @Override
  public void visitCode() {
    visitMethodInsn(Opcodes.INVOKESTATIC, "cn/think/in/java/learing/loader/asm/stat/time/TimeStat", "start", "()V");
    super.visitCode();
  }

  @Override
  public void visitInsn(int opcode) {
    if ((opcode >= IRETURN) && opcode <= RETURN) {
      visitMethodInsn(Opcodes.INVOKESTATIC,
          "cn/think/in/java/learing/loader/asm/stat/time/TimeStat", "end", "()V");

    }
    mv.visitInsn(opcode);
  }
}
