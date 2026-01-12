package com.guyan.ioc.convert;

public class SimpleTypeConverter implements TypeConverter {

    @Override
    public Object convert(String value, Class<?> targetType) {

        if (targetType == String.class) {
            return value;
        }

        if (targetType == int.class || targetType == Integer.class) {
            return Integer.valueOf(value);
        }

        if (targetType == long.class || targetType == Long.class) {
            return Long.valueOf(value);
        }

        if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.valueOf(value);
        }
        if (targetType == double.class || targetType == Double.class) {
            return Double.valueOf(value);
        }

        throw new IllegalArgumentException("Unsupported target type: " + targetType);
    }

}
