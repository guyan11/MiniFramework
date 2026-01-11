package com.guyan.ioc.core;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class BeanDefinition {

    private String beanId;
    private String className;

}
