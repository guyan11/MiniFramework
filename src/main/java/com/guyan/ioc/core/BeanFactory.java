package com.guyan.ioc.core;

public interface BeanFactory {
    Object getBean(String name);

    Object getBeanByType(Class<?> type);
}
