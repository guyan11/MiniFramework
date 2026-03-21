package com.guyan.ioc.invocation;

import java.lang.reflect.Method;

public class NameMatchMethodPointCut implements PointCut {

    private final String methodName;

    public NameMatchMethodPointCut(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals(methodName);
    }
}
