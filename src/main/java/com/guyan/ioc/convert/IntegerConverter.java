package com.guyan.ioc.convert;

public class IntegerConverter extends AbstractTypeConverter implements TypeConverter {
    @Override
    protected Object convertInternal(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throwConversionException(value, "Integer");
            // 永不执行，用于编译通过
            return null;
        }
    }
}
