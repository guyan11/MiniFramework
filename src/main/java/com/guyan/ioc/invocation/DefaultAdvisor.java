package com.guyan.ioc.invocation;

import com.guyan.ioc.interceptor.MethodInterceptor;

public class DefaultAdvisor implements Advisor {

    private final PointCut pointCut;

    private final MethodInterceptor methodInterceptor;

    public DefaultAdvisor(PointCut pointCut, MethodInterceptor methodInterceptor) {
        this.pointCut = pointCut;
        this.methodInterceptor = methodInterceptor;
    }

    @Override
    public PointCut getPointCut() {
        return pointCut;
    }

    @Override
    public MethodInterceptor getInterceptor() {
        return methodInterceptor;
    }
}
