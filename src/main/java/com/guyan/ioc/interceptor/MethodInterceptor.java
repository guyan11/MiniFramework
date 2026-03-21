package com.guyan.ioc.interceptor;

import com.guyan.ioc.invocation.MethodInvocation;

public interface MethodInterceptor {

    Object invoke(MethodInvocation invocation) throws Throwable;

}