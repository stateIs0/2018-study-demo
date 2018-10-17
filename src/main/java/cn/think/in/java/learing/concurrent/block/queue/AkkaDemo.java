package cn.think.in.java.learing.concurrent.block.queue;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class AkkaDemo {

  public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("demoSystem");
    ActorRef test1 = system.actorOf(Props.create(Actor.class), "test1");
    ActorRef test2 = system.actorOf(Props.create(Actor.class), "test2");
    for (int i = 3; i < 100000; i++) {
//      system.actorOf(Props.create(Actor.class), "Test" + i).tell("Test" + i + " msg", null);
      test1.tell("test1 msg", null);
    }
  }

}

class Actor extends UntypedActor {

  @Override
  public void onReceive(Object o) throws Exception {
    for (; ; ) {
      Thread.sleep(1000);
      System.out.println(o);
    }
  }
}
