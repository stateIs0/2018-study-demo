//
//package cn.think.in.java.learing.spi;
//
//import java.lang.annotation.Annotation;
//
//public class Client {
//  public static void main(String[] args) {
//    //可以通过I/O操作或配置项获得包名
//    String pkgName = "cn.think.in.java.learing.spi";
//    Package pkg = Package.getPackage(pkgName);
//    //获得包上的注解
//    Annotation[] annotations = pkg.getAnnotations();
//    //遍历注解数组
//    for(Annotation an:annotations){
//      if(an instanceof PkgAnnotation){
//        System.out.println("Hi,I'm the PkgAnnotation");
//      }
//    }
//  }
//}
