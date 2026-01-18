package com.guyan.ioc.core;

import com.guyan.service.UserService;
import org.junit.jupiter.api.Test;

class XmlApplicationContextTest {

    @Test
    public void getBean() throws Exception {
        XmlApplicationContext context = new XmlApplicationContext("META-INF/beans.xml");
        Object bean = context.getBean("userService");
        if (bean instanceof UserService) {
            UserService userService = (UserService) bean;
            userService.hello();
            userService.user();
        }
    }
}