package com.guyan.ioc.convert;

public class StringConverter extends AbstractTypeConverter implements TypeConverter {

    @Override
    protected Object convertInternal(String value) {
        return value;
    }
}