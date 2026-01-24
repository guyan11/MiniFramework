package com.guyan.ioc.core;

public interface BeanWrapper {

    Object getWrappedInstance();

    Class<?> getWrappedClass();

    void setPropertyValue(String propertyName, String value, Object ref) throws NoSuchFieldException, IllegalAccessException;
}
