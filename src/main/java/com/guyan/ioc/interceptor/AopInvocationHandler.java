package com.guyan.ioc.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AopInvocationHandler implements InvocationHandler {

    private final Object target;

    private final MethodInterceptor methodInterceptor;

    public AopInvocationHandler(Object target, MethodInterceptor methodInterceptor) {
        this.target = target;
        this.methodInterceptor = methodInterceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return methodInterceptor.invoke(method, args, target);
    }
}
