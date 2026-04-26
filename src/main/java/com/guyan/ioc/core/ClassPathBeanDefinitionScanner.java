package com.guyan.ioc.core;

import com.guyan.ioc.annonation.Component;
import com.guyan.ioc.utils.StringUtil;

import java.io.File;
import java.net.URL;

public class ClassPathBeanDefinitionScanner {


    private final DefaultBeanFactory beanFactory;

    public ClassPathBeanDefinitionScanner(DefaultBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void doScan(String basePackage) {
        if (StringUtil.isEmpty(basePackage)) {
            throw new IllegalArgumentException("basePackage is empty");
        }

        String filePath = basePackage.replace(".", "/");

        URL resource = getClass().getClassLoader().getResource(filePath);

        if (resource == null) {
            throw new IllegalArgumentException("resource is null");
        }
        File dir = new File(resource.getFile());

        if (!dir.exists()) {
            throw new IllegalArgumentException("dir is not exists");
        }

        for (File file : dir.listFiles()) {
            if (file.getName().endsWith(".class")) {
                String className = basePackage + "." + file.getName().replace(".class", "");

                try {
                    Class<?> clazz = Class.forName(className);

                    if (clazz.isAnnotationPresent(Component.class)) {
                        Component component = clazz.getAnnotation(Component.class);
                        String beanName = component.value();
                        if (StringUtil.isEmpty(beanName)) {
                            beanName = lowerFirst(clazz.getSimpleName());
                        }
                        BeanDefinition beanDefinition = new BeanDefinition(beanName, className);
                        beanFactory.registerBeanDefinition(beanName, beanDefinition);
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private String lowerFirst(String beanName) {
        return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
    }
}
