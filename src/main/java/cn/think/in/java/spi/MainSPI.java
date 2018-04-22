package cn.think.in.java.spi;

import java.util.ServiceLoader;

public class MainSPI {

  public static void main(String[] args) {
    ServiceLoader<People> loaders = ServiceLoader.load(People.class);
    for (People p : loaders) {
      p.printMyName();

    }
  }
}
