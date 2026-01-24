package com.guyan.ioc.convert;

public class SimpleTypeConverter implements TypeConverter {

    private final TypeConverterFactory factory;

    public SimpleTypeConverter() {
        this.factory = new TypeConverterFactory();
    }

    public SimpleTypeConverter(TypeConverterFactory factory) {
        this.factory = factory;
    }

    @Override
    public Object convert(Object value, Class<?> targetType) {
        return factory.convert(value, targetType);
    }

    public void registerConverter(Class<?> targetType, TypeConverter converter) {
        factory.registerConverter(targetType, converter);
    }

    // @Override
    // public Object convert(String value, Class<?> targetType) {
    //
    //     if (targetType == String.class) {
    //         return value;
    //     }
    //
    //     if (targetType == int.class || targetType == Integer.class) {
    //         return Integer.valueOf(value);
    //     }
    //
    //     if (targetType == long.class || targetType == Long.class) {
    //         return Long.valueOf(value);
    //     }
    //
    //     if (targetType == boolean.class || targetType == Boolean.class) {
    //         return Boolean.valueOf(value);
    //     }
    //     if (targetType == double.class || targetType == Double.class) {
    //         return Double.valueOf(value);
    //     }
    //
    //     throw new IllegalArgumentException("Unsupported target type: " + targetType);
    // }


}
