package com.guyan.ioc.core;

public interface ObjectFactory<T> {

    T getObject() throws Exception;
}
