package cn.think.in.java.learing.loader.asm;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class SecurityWeaveGenerator {

  public static void main(String[] args) throws IOException {
    String className = Account.class.getName();
    ClassReader cr = new ClassReader(className);
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
    AddSecurityCheckClassAdapter checkClassAdapter = new AddSecurityCheckClassAdapter(cw);
    cr.accept(checkClassAdapter, ClassReader.SKIP_DEBUG);
    byte[] data = cw.toByteArray();
    File file = new File("target/classes/" + className.replaceAll("\\.", "/") + ".class");
    FileOutputStream fout = new FileOutputStream(file);
    fout.write(data);
    fout.close();

  }

}/*target/classes/cn/think/in/java/clazz/loader/agent/Account.class*/


class Acco1untMain {

  /**
   * -javaagent:E:\self\demo\out\artifacts\test\test.jar=cxsTest
   */
  public static void main(String[] args)
      throws ClassNotFoundException, InterruptedException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

    while (true) {
      ClassLoader loader = new ClassLoader() {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
          try {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

            InputStream is = getClass().getResourceAsStream(fileName);
            if (is == null) {
              return super.loadClass(name);
            }

            byte[] b = new byte[is.available()];

            is.read(b);
            return defineClass(name, b, 0, b.length);

          } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException(name);
          }
        }
      };

      Class clazz = loader.loadClass("cn.think.in.java.learing.loader.asm.Account");

//      Account account = new Account();
//      account.operation();
//      bccount.test();

      Object account = clazz.newInstance();
      account.getClass().getMethod("operation", new Class[]{}).invoke(account);
      Thread.sleep(10000);
    }
  }

}


class Ac1countMain {

  /**
   * -javaagent:E:\self\demo\out\artifacts\test\test.jar=cxsTest
   */
  public static void main(String[] args)
      throws InterruptedException {
    Account account = new Account();
    account.operation();
  }

}

class JVMTIThread {

  public static void main(String[] args)
      throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
    List<VirtualMachineDescriptor> list = VirtualMachine.list();
    for (VirtualMachineDescriptor vmd : list) {
      if (vmd.displayName().endsWith("AccountMain")) {
        VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
        virtualMachine.loadAgent("E:\\self\\demo\\out\\artifacts\\test\\test.jar ", "cxs");
        System.out.println("ok");
        virtualMachine.detach();
        break;

      }

    }
  }
}

class AddSecurityCheckClassAdapter extends ClassVisitor {

  public AddSecurityCheckClassAdapter(ClassVisitor cv) {
    super(Opcodes.ASM5, cv);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc, String signature,
      String[] exceptions) {
    MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
    MethodVisitor wrappedMv = mv;
    if (mv != null) {
      if (name.equals("operation")) {
        wrappedMv = new AddSecurityCheckMethodAdapter(mv);
      }
    }
    return wrappedMv;

  }
}
