package com.guyan.ioc.lifecycle;

import com.guyan.ioc.interceptor.AopInvocationHandler;
import com.guyan.ioc.interceptor.LogMethodInterceptor;
import com.guyan.ioc.interceptor.MethodInterceptor;

import java.lang.reflect.Proxy;

public class AopBeanPostProcessor implements BeanPostProcessor {

    private final MethodInterceptor methodInterceptor = new LogMethodInterceptor();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        Class<?> clazz = bean.getClass();

        Class<?>[] interfaces = clazz.getInterfaces();

        if (interfaces.length < 1) {
            return bean;
        }

        return Proxy.newProxyInstance(
                clazz.getClassLoader(),
                interfaces,
                new AopInvocationHandler(bean, methodInterceptor)
        );
    }
}
