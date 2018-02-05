package cn.think.in.java.clazz.loader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {

    System.gc();//1

    ClassLoader[] arr = new ClassLoader[10000];

    for (int i = 0; i < 10000; i++) {

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
      arr[i] = loader;
    }

    System.gc();//2

    Class[] cArr = new Class[100];
    Object[] oArr = new Object[100];

    for (int i = 0; i < 100; i++) {
//      cArr[i] = arr[i].loadClass("cn.think.in.java.clazz.loader.ClassLoaderDemo");
//      oArr[i] = cArr[i].newInstance();
    }

    System.gc();//3

    for (int i = 0; i < 10000; i++) {
      arr[i] = null;
//      oArr[i] = null;
//      cArr[i] = null;
    }

    System.gc();//4
    System.gc();//5

//    System.out.println(o.getClass());
//    System.out.println(o instanceof ClassLoaderDemo);
//    System.err.println(o.getClass().getClassLoader());
//    System.err.println(ClassLoaderDemo.class.getClassLoader());
//    System.err.println(ClassLoaderDemo.class.getClassLoader().getParent());
//    System.err.println(ClassLoaderDemo.class.getClassLoader().getParent().getParent());

  }

}
