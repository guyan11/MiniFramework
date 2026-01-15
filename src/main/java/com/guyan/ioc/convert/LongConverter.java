package com.guyan.ioc.convert;

public class LongConverter extends AbstractTypeConverter implements TypeConverter {

    @Override
    protected Object convertInternal(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            throwConversionException(value, "Long");
            // 永不执行，用于编译通过
            return null;
        }
    }
}