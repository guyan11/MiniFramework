package com.guyan.ioc.convert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class TypeConverterFactoryTest {

    TypeConverterFactory typeConverterFactory;

    /**
     * 注册转换器
     */
    @BeforeEach
    void registerConverter() {
        typeConverterFactory = new TypeConverterFactory();
        typeConverterFactory.registerConverter(Date.class, new AbstractTypeConverter() {
            @Override
            protected Object convertInternal(String value) {
                if (value == null) {
                    throw new IllegalArgumentException("Value cannot be null");
                }
                try {
                    return new Date(Long.parseLong(value));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Failed to convert '" + value + "' to Date");
                }
            }
        });
    }


    /**
     * 测试转换
     */
    @Test
    void convert() {
        Date date = (Date) typeConverterFactory.convert("1645123456789", Date.class);
        System.out.println(date);
    }
}