package cn.think.in.java.learing.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Bean 定义解析器
 *
 * 此类用于解析 XML 元素中的值，并添加到 beanDefinition 中。
 * 一般在 NamespaceHandlerSupport 中使用，比如继承 NamespaceHandlerSupport，在其 init 方法中进行注册。
 * NamespaceHandlerSupport 的 init 方法会被执行。
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

  @Override
  protected Class<?> getBeanClass(Element element) {
    return User.class;
  }

  @Override
  protected void doParse(Element element, BeanDefinitionBuilder builder) {
    String userName = element.getAttribute("userName");
    String email = element.getAttribute("email");
    if (StringUtils.hasText(userName)) {
      builder.addPropertyValue("userName", userName);
    }
    if (StringUtils.hasText(email)) {
      builder.addPropertyValue("email", email);
    }
  }
}