package cn.think.in.java.learing.spi;

import java.util.ServiceLoader;

public class MainSPI {

  public static void main(String[] args) {
    ServiceLoader<People> loaders = ServiceLoader.load(People.class);
    if (loaders.iterator().hasNext()) {
      loaders.iterator().next().printMyName();
    }
    for (People p : loaders) {
      p.printMyName();
    }
  }
}
