package com.guyan.ioc.convert;

public interface TypeConverter {

    Object convert(String value, Class<?> targetType);
}
