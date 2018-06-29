package cn.think.in.java.learing.concurrent.lock.condition;

public class ConditionDeadLockDemo extends Thread{

  int state;

  public ConditionDeadLockDemo(int state, String name) {
    this.state = state;
    super.setName(name);
  }

  @Override
  public void run() {

    if (state == 1){

    } else if (state  == 2){

    }

  }
}
