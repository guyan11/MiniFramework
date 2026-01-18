package com.guyan.ioc.core;

public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
