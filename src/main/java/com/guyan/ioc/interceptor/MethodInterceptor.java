package com.guyan.ioc.interceptor;

import java.lang.reflect.Method;

public interface MethodInterceptor {

    Object invoke(Method method, Object[] args, Object target) throws Throwable;

}