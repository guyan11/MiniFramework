package com.guyan.ioc.invocation;

import com.guyan.ioc.interceptor.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.List;

public class ReflectMethodInvocation implements MethodInvocation {


    private final Object target;
    private final Method method;
    private final Object[] args;
    private int index = -1;
    private final List<MethodInterceptor> interceptors;

    public ReflectMethodInvocation(Object target, Method method, Object[] args,
                                   List<MethodInterceptor> interceptors) {
        this.target = target;
        this.method = method;
        this.args = args;
        this.interceptors = interceptors;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object getTarget() {
        return target;
    }

    @Override
    public Object proceed() throws Throwable {

        if (index == interceptors.size() - 1) {
            return method.invoke(target, args);
        }
        index++;
        MethodInterceptor interceptor = interceptors.get(index);
        return interceptor.invoke(this);
    }
}
