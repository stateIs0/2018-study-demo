package cn.think.in.java.clazz.loader.agent;

import java.lang.instrument.Instrumentation;

/**
 * 在 src 目录下添加 META-INF/MANIFEST.MF 文件，内容按如下定义：

   Manifest-Version: 1.0
   Premain-Class: com.shanhy.demo.agent.MyAgent
   Can-Redefine-Classes: true

   java -javaagent:G:\myagent.jar=Hello1 -javaagent:G:\myagent.jar=Hello2 -javaagent:G:\myagent.jar=Hello3 -jar myapp.jar
   特别提醒：如果你把 -javaagent 放在 -jar 后面，则不会生效。也就是说，放在主程序后面的 agent 是无效的。
 */
public class MyAgent {

  /**
   * 该方法在main方法之前运行，与main方法运行在同一个JVM中
   * 并被同一个System ClassLoader装载
   * 被统一的安全策略(security policy)和上下文(context)管理
   *
   * @param agentOps
   * @param inst
   * @author SHANHY
   * @create 2016年3月30日
   */
  public static void premain(String agentOps, Instrumentation inst) {
    System.out.println("=========premain方法执行========");
    System.out.println(agentOps);
  }

  /**
   * 如果不存在 premain(String agentOps, Instrumentation inst)
   * 则会执行 premain(String agentOps)
   *
   * @param agentOps
   * @author SHANHY
   * @create 2016年3月30日
   */
  public static void premain(String agentOps) {
    System.out.println("=========premain方法执行2========");
    System.out.println(agentOps);
  }
}
