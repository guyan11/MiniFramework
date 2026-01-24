package com.guyan.ioc.core;

import com.guyan.ioc.convert.TypeConverterFactory;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class DefaultBeanWrapper implements BeanWrapper {

    private final Object wrappedInstance;

    private final TypeConverterFactory typeConverterFactory;

    public DefaultBeanWrapper(Object wrappedInstance, TypeConverterFactory typeConverterFactory) {
        this.wrappedInstance = wrappedInstance;
        this.typeConverterFactory = typeConverterFactory;
    }

    @Override
    public Object getWrappedInstance() {
        return wrappedInstance;
    }

    @Override
    public Class<?> getWrappedClass() {
        return wrappedInstance.getClass();
    }

    @Override
    public void setPropertyValue(String propertyName, Object value) {
        try {
            Field field = wrappedInstance.getClass().getDeclaredField(propertyName);

            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Object convertValue = typeConverterFactory.convert(value, field.getType());

            field.set(wrappedInstance, convertValue);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set property" + propertyName + " with value", e);
        }
    }

}
