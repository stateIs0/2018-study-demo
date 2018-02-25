package cn.think.in.java.clazz.loader.asm.stat.time;

import cn.think.in.java.clazz.loader.asm.Account;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

public class TimeStatWeaveGenerator {

  public static void main(String[] args) throws IOException {
    String className = Account.class.getName();
    ClassReader cr = new ClassReader(className);
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

    TimeStatClassAdapter classAdapter = new TimeStatClassAdapter(cw);

    cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
    byte[] data = cw.toByteArray();
    File file = new File("target/classes/" + className.replaceAll("\\.", "/") + ".class");
    FileOutputStream fout = new FileOutputStream(file);
    fout.write(data);
    fout.close();
  }

}
