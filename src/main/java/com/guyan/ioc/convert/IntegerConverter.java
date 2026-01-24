package com.guyan.ioc.convert;

public class IntegerConverter extends AbstractTypeConverter implements TypeConverter {
    @Override
    protected Object convertInternal(Object value) {
        try {

            return Integer.valueOf(value.toString());
        } catch (NumberFormatException e) {
            throwConversionException(value.toString(), "Integer");
            // 永不执行，用于编译通过
            return null;
        }
    }
}
