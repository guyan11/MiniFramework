package com.guyan.ioc.convert;

public class LongConverter extends AbstractTypeConverter implements TypeConverter {

    @Override
    protected Object convertInternal(Object value) {
        try {
            return Long.valueOf(value.toString());
        } catch (NumberFormatException e) {
            throwConversionException(value.toString(), "Long");
            // 永不执行，用于编译通过
            return null;
        }
    }
}