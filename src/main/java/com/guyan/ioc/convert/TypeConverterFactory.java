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

    public Object convert(Object value, Class<?> targetType) {

        if (value == null) {
            return null;
        }

        // 如果类型匹配，则直接返回
        if (targetType.isAssignableFrom(value.getClass())) {
            return value;
        }

        TypeConverter typeConverter = converterMap.get(targetType);
        if (typeConverter != null) {
            return typeConverter.convert(value, targetType);
        }
        throw new IllegalArgumentException("No converter found for target type: " + targetType);
    }

}
