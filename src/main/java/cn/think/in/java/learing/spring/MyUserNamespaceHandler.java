package cn.think.in.java.learing.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 定义一个nameSpaceHandler，用于注册到Spring框架里面，在spring解析算定义标签的时候可以用我们自定义的处理方式。
 *
 * spring.handlers 文件指向此处。使用一个 URL 指向该类。
 * spring.schemas 则指向了 xsd 文件，用于标签的定义。
 */
public class MyUserNamespaceHandler extends NamespaceHandlerSupport {

  @Override
  public void init() {
    registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
  }
}