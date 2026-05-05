package com.guyan.ioc.lifecycle;

import com.guyan.ioc.annonation.Autowired;
import com.guyan.ioc.core.DefaultBeanFactory;

import java.lang.reflect.Field;

public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final DefaultBeanFactory defaultBeanFactory;

    public AutowiredAnnotationBeanPostProcessor(DefaultBeanFactory defaultBeanFactory) {
        this.defaultBeanFactory = defaultBeanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        Field[] fields = bean.getClass().getDeclaredFields();
        if (fields.length < 1) {
            return bean;
        }

        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    Object dependencyBean = defaultBeanFactory.getBean(field.getName());
                    field.set(bean, dependencyBean);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return bean;
    }
}
