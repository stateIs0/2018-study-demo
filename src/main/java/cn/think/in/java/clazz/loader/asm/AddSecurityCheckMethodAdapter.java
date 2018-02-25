package cn.think.in.java.clazz.loader.asm;

import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class AddSecurityCheckMethodAdapter extends MethodVisitor {

  public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
    super(Opcodes.ASM5, mv);
  }

  public void visitCode() {
    Label continueLabel = new Label();
    visitMethodInsn(Opcodes.INVOKESTATIC, "cn/think/in/java/clazz/loader/agent/SecurityChecker",
        "checkSecurity", "()Z");
    visitJumpInsn(Opcodes.IFNE, continueLabel);
    visitInsn(Opcodes.RETURN);
    visitLabel(continueLabel);
    super.visitCode();
  }
}
