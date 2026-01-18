package com.guyan.ioc.core;

public interface SingletonRegistry {

    Object getSingletonBean(String name);

    void registerSingleton(String name, Object bean);
}
