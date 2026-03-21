package com.guyan.ioc.core;

import com.guyan.ioc.interceptor.LogMethodInterceptor;
import com.guyan.ioc.interceptor.MethodInterceptor;
import com.guyan.ioc.invocation.Advisor;
import com.guyan.ioc.invocation.DefaultAdvisor;
import com.guyan.ioc.invocation.NameMatchMethodPointCut;
import com.guyan.ioc.invocation.PointCut;
import com.guyan.ioc.lifecycle.AopBeanPostProcessor;
import com.guyan.ioc.lifecycle.AutoProxyCreator;
import com.guyan.ioc.lifecycle.LogBeanPostProcessor;
import com.guyan.service.UserService;
import com.guyan.service.UserServiceAop;
import org.junit.jupiter.api.Test;

import java.util.Collections;

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

    @Test
    public void testAopProxy() throws Exception {
        XmlApplicationContext context = new XmlApplicationContext("META-INF/beans-processor.xml");
        DefaultBeanFactory beanFactory = context.getBeanFactory();
        beanFactory.addBeanPostProcessor(new AopBeanPostProcessor());
        UserServiceAop userServiceAop = (UserServiceAop) context.getBean("userServiceAop");
        userServiceAop.test();
    }

    @Test
    public void testAopProxyPoint() throws Exception {
        XmlApplicationContext context = new XmlApplicationContext("META-INF/beans-processor.xml");
        DefaultBeanFactory beanFactory = context.getBeanFactory();
        // 1. Pointcut
        PointCut pointcut = new NameMatchMethodPointCut("test");

        // 2. Advice
        MethodInterceptor interceptor = new LogMethodInterceptor();

        // 3. Advisor
        Advisor advisor = new DefaultAdvisor(pointcut, interceptor);

        // 4. 注册 AutoProxyCreator
        AutoProxyCreator apc = new AutoProxyCreator();
        apc.setAdvisors(Collections.singletonList(advisor));

        beanFactory.addBeanPostProcessor(apc);
        UserServiceAop userServiceAop = (UserServiceAop) context.getBean("userServiceAop");
        userServiceAop.test();
    }

}
