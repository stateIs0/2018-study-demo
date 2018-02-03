package cn.think.in.java.clazz.loader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
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

    Object o = loader.loadClass("cn.think.in.java.clazz.loader.ClassLoaderDemo").newInstance();
    System.out.println(o.getClass());
    System.out.println(o instanceof ClassLoaderDemo);
    System.err.println(o.getClass().getClassLoader());
    System.err.println(ClassLoaderDemo.class.getClassLoader());
    System.err.println(ClassLoaderDemo.class.getClassLoader().getParent());
    System.err.println(ClassLoaderDemo.class.getClassLoader().getParent().getParent());


  }

}
