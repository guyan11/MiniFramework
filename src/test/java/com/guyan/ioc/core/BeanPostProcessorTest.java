package com.guyan.ioc.core;

import com.guyan.ioc.lifecycle.LogBeanPostProcessor;
import com.guyan.service.UserService;
import org.junit.jupiter.api.Test;

public class BeanPostProcessorTest {

    @Test
    public void testBeanProcessorByManual() throws Exception {
        XmlApplicationContext context = new XmlApplicationContext("META-INF/beans-processor.xml");
        DefaultBeanFactory beanFactory = context.getBeanFactory();
        beanFactory.addBeanPostProcessor(new LogBeanPostProcessor());
        Object bean = context.getBean("userService");
        if (bean instanceof UserService) {
            UserService userService = (UserService) bean;
            userService.hello();
        }
    }


    @Test
    public void testBeanProcessorByAutowire() throws Exception {
        XmlApplicationContext context = new XmlApplicationContext("META-INF/beans-processor.xml");
        DefaultBeanFactory beanFactory = context.getBeanFactory();
        beanFactory.registerBeanPostProcessors();
        Object bean = context.getBean("userService");
        if (bean instanceof UserService) {
            UserService userService = (UserService) bean;
            userService.hello();
        }
    }

}
