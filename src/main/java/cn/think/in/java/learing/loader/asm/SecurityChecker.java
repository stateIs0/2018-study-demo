package cn.think.in.java.learing.loader.asm;

public class SecurityChecker {

  public static boolean checkSecurity() {
    System.out.println("SecurityChecker.checkSecurity ...");
//    if ((System.currentTimeMillis() & 1) == 0) {
//      return false;
//    }
    return true;
  }

}
