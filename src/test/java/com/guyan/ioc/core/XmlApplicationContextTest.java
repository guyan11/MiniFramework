package com.guyan.ioc.core;

import com.guyan.service.OrderServiceCircle;
import com.guyan.service.UserService;
import com.guyan.service.UserServiceCircle;
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

    @Test
    public void getBeanCircle() throws Exception {
        XmlApplicationContext context = new XmlApplicationContext("META-INF/beans.xml");
        Object bean = context.getBean("orderServiceCircle");
        if (bean instanceof OrderServiceCircle) {
            OrderServiceCircle orderServiceCircle = (OrderServiceCircle) bean;
            orderServiceCircle.circle();
        }

        Object userServiceCircle = context.getBean("userServiceCircle");
        if (userServiceCircle instanceof UserServiceCircle) {
            UserServiceCircle userServiceCircle1 = (UserServiceCircle) userServiceCircle;
            userServiceCircle1.circle();
        }
    }
}