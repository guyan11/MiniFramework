package com.guyan.ioc.core;

import com.guyan.ioc.lifecycle.AutowiredAnnotationBeanPostProcessor;
import com.guyan.service.UserService;
import org.junit.jupiter.api.Test;

public class AutoWireTest {

    @Test
    public void test() {

        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(defaultBeanFactory);

        scanner.doScan("com.guyan.service");

        defaultBeanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor(defaultBeanFactory));

        UserService userService = (UserService) defaultBeanFactory.getBean("userService");

        userService.hello();
    }
}
