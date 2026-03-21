package com.guyan.ioc.invocation;

import com.guyan.ioc.interceptor.MethodInterceptor;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxyFactory {

    private final Object target;

    private final List<Advisor> advisors;

    public ProxyFactory(Object target, List<Advisor> advisors) {
        this.target = target;
        this.advisors = advisors;
    }

    public Object getProxy() {

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                ((proxy, method, args) -> {
                    List<MethodInterceptor> interceptors = new ArrayList<>();
                    for (Advisor advisor : advisors) {
                        boolean matches = advisor.getPointCut().matches(method, target.getClass());
                        if (matches) {
                            interceptors.add(advisor.getInterceptor());
                        }
                    }
                    MethodInvocation invocation = new ReflectMethodInvocation(target, method, args, interceptors);
                    return invocation.proceed();
                }));
    }
}
