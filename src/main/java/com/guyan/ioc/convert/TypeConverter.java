package com.guyan.ioc.convert;

public interface TypeConverter {

    Object convert(Object value, Class<?> targetType);
}
