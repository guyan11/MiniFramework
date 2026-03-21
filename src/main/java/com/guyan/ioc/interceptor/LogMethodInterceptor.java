package com.guyan.ioc.interceptor;

import com.guyan.ioc.invocation.MethodInvocation;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class LogMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object[] args = invocation.getArguments();
        Object target = invocation.getTarget();
        log.info("before invoke method:{}", method.getName());
        Object result = method.invoke(target, args);
        log.info("after invoke method:{}", method.getName());
        return result;
    }
}
