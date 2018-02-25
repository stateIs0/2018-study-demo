package cn.think.in.java.clazz.loader.ibm;

public class TestMainInJar {

  // java – javaagent:TestInstrument1.jar – cp TestInstrument1.jar TestMainInJar
  //  Manifest-Version: 1.0
  //  Premain-Class: Premain


  // java – javaagent:TestInstrument2.jar – cp TestInstrument2.jar TestMainInJar
  //  Manifest-Version: 1.0
  //  Agent-Class: AgentMain
  public static void main(String[] args) throws InterruptedException {
//    System.out.println(new TransClass().getNumber());
    System.out.println(new TransClass().getNumber());
    int count = 0;
    while (true) {
      Thread.sleep(500);
      count++;
      int number = new TransClass().getNumber();
      System.out.println(number);
      if (3 == number || count >= 10) {
        break;
      }
    }
  }
}
