package com.guyan.ioc.interceptor;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class LogMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(Method method, Object[] args, Object target) throws Throwable {
        log.info("before invoke method:{}", method.getName());
        Object result = method.invoke(target, args);
        log.info("after invoke method:{}", method.getName());
        return result;
    }
}
