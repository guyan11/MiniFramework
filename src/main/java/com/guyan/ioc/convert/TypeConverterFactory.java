package com.guyan.ioc.convert;

import java.util.HashMap;
import java.util.Map;

public class TypeConverterFactory {

    private final Map<Class<?>, TypeConverter> converterMap = new HashMap<>();

    public TypeConverterFactory() {
        registerDefaultConverter();
    }

    private void registerDefaultConverter() {
        registerConverter(String.class, new StringConverter());

        registerConverter(int.class, new IntegerConverter());
        registerConverter(Integer.class, new IntegerConverter());
    }


    public void registerConverter(Class<?> targetType, TypeConverter typeConverter) {
        if (targetType != null && typeConverter != null) {
            converterMap.put(targetType, typeConverter);
        }
    }

    public Object convert(String value, Class<?> targetType) {
        TypeConverter typeConverter = converterMap.get(targetType);
        if (typeConverter != null) {
            return typeConverter.convert(value, targetType);
        }
        throw new IllegalArgumentException("No converter found for target type: " + targetType);
    }

}
