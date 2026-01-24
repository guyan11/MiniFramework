package com.guyan.ioc.convert;

public class StringConverter extends AbstractTypeConverter implements TypeConverter {

    @Override
    protected Object convertInternal(Object value) {
        return value.toString();
    }
}