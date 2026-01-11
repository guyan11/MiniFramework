package com.guyan.ioc.core;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Data
public class BeanDefinition {

    private String beanId;

    private String className;

    public BeanDefinition(String beanId, String className) {
        this.beanId = beanId;
        this.className = className;
    }

    // 新增：属性注入信息
    private List<PropertyValue> propertyValues = new ArrayList<>();

}
