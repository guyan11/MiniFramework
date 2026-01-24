package com.guyan.ioc.core;

public interface BeanWrapper {

    Object getWrappedInstance();

    Class<?> getWrappedClass();

    void setPropertyValue(String propertyName, Object value) throws NoSuchFieldException, IllegalAccessException;
}
