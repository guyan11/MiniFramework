package com.guyan.ioc.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyValue {

    // 属性名
    private String name;

    // 属性值,引用的 beanId
    private String ref;
}
