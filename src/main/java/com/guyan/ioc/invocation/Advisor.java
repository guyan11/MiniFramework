package com.guyan.ioc.invocation;

import com.guyan.ioc.interceptor.MethodInterceptor;

public interface Advisor {

    PointCut getPointCut();

    MethodInterceptor getInterceptor();
}
