package com.guyan.ioc.lifecycle;

public class LogBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        System.out.println("Before Init: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        System.out.println("After Init: " + beanName);
        return bean;
    }
}