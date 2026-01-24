package com.guyan.ioc.convert;

public abstract class AbstractTypeConverter implements TypeConverter {

    @Override
    public Object convert(Object value, Class<?> targetType) {
        // 通用校验逻辑
        validateInput(value);

        // 委托给子类实现具体的转换逻辑
        return convertInternal(value);
    }

    /**
     * 输入参数校验（可被子类覆盖）
     */
    protected void validateInput(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
    }

    /**
     * 子类实现具体的转换逻辑
     */
    protected abstract Object convertInternal(Object value);

    /**
     * 可选：通用的工具方法，供子类使用
     */
    protected void throwConversionException(String value, String typeName) {
        throw new IllegalArgumentException(
                String.format("Failed to convert '%s' to %s", value, typeName)
        );
    }
}
