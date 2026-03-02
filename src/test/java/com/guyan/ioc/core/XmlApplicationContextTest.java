package com.guyan.ioc.core;

import com.guyan.ioc.domain.User;
import com.guyan.service.OrderServiceCircle;
import com.guyan.service.UserService;
import com.guyan.service.UserServiceCircle;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
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
    public void getBeanCircle() {
        try {
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
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Test
    public void getBeanByEditor() throws Exception {
        XmlApplicationContext context = new XmlApplicationContext("META-INF/beans.xml");
        DefaultBeanFactory beanFactory = context.getBeanFactory();
        beanFactory.registerBeanPostProcessors();
        Object bean = context.getBean("user");
        if (bean instanceof User) {
            User user = (User) bean;
            System.out.println(user);
        }
    }
}