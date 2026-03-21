package com.guyan.ioc.lifecycle;

import com.guyan.ioc.interceptor.MethodInterceptor;
import com.guyan.ioc.invocation.Advisor;
import com.guyan.ioc.invocation.ProxyFactory;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Data
public class AutoProxyCreator implements BeanPostProcessor {

    private List<Advisor> advisors = new ArrayList<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (bean instanceof Advisor || bean instanceof MethodInterceptor) {
            return bean;
        }

        List<Advisor> matchedAdvisor = new ArrayList<>();
        for (Advisor advisor : advisors) {
            for (Method method : bean.getClass().getMethods()) {
                boolean match = advisor.getPointCut().matches(method, bean.getClass());
                if (match) {
                    matchedAdvisor.add(advisor);
                    break;
                }
            }
        }

        if (matchedAdvisor.isEmpty()) {
            return bean;
        }
        return new ProxyFactory(bean, matchedAdvisor).getProxy();
    }
}
