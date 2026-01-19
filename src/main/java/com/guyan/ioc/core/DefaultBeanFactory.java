package com.guyan.ioc.core;

import com.guyan.ioc.convert.TypeConverterFactory;
import com.guyan.ioc.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DefaultBeanFactory implements BeanFactory, SingletonRegistry, BeanDefinitionRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private final TypeConverterFactory typeConverter = new TypeConverterFactory();

    // 提前暴露的单例对象
    private final Map<String, Object> earlySingletonObjects = new HashMap<>();

    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    @Override
    public Object getBean(String name) {
        Object bean = getSingletonBean(name);
        if (bean != null) {
            return bean;
        }

        Object earlySingletonBean = getEarlySingleton(name);
        if (earlySingletonBean != null) {
            return earlySingletonBean;
        }

        Object factoryBean;
        try {
            factoryBean = getFactoryBean(name);
            if (factoryBean != null) {
                registerEarlySingleton(name, factoryBean);
                return factoryBean;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        BeanDefinition beanDefinition = getBeanDefinition(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No such bean '" + name + "' is defined");
        }

        return createBean(name, beanDefinition);
    }

    private Object createBean(String name, BeanDefinition bd) {
        String className = bd.getClassName();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
            Object bean = clazz.newInstance();

            // 注册单例工厂
            registerSingletonFactory(name, () -> getEarlyBeanReference(bean));
            // 注入属性
            populateBeanProperties(bean, bd);
            // 提前暴露单例对象
            registerEarlySingleton(name, bean);
            // 注册单例对象
            registerSingleton(name, bean);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object getEarlyBeanReference(Object bean) {
        return bean;
    }

    private void populateBeanProperties(Object bean, BeanDefinition bd) {
        boolean empty = bd.getPropertyValues().isEmpty();
        if (empty) {
            log.warn("beanDefinition 中没有 property 定义");
            return;
        }

        try {
            for (PropertyValue propertyValue : bd.getPropertyValues()) {
                String propertyName = propertyValue.getName();
                String ref = propertyValue.getRef();
                String value = propertyValue.getValue();

                // 1. 先从单例池中获取 refBean
                // Object refBean = null;
                // if (StringUtil.isNotBlank(ref)) {
                //     refBean = singletonObjects.get(ref);
                //     if (refBean == null) {
                //         log.warn("refBean 不存在");
                //         continue;
                //     }
                // }

                // 1. 通过反射设置属性值,后续放开
                // try {
                //     Field field = bean.getClass().getField(name);
                //     if (!field.isAccessible()) {
                //         field.setAccessible(true);
                //     }
                //     field.set(bean, refBean);
                // } catch (Exception e) {
                //     log.error("populateBean 失败", e);
                //     throw new RuntimeException(e);
                // }

                // 2. 拼 setter 方法名
                String setterMethodName =
                        "set" + propertyName.substring(0, 1).toUpperCase()
                                + propertyName.substring(1);

                // 3. 找 setter 方法
                Method[] methods = bean.getClass().getMethods();
                Object injectValue;
                for (Method method : methods) {
                    if (method.getName().equals(setterMethodName)) {

                        if (StringUtil.isNotEmpty(value)) {
                            Class<?> parameterType = method.getParameterTypes()[0];
                            // 4. 普通参数，类型转换
                            injectValue = typeConverter.convert(propertyValue.getValue(), parameterType);
                        } else {
                            injectValue = getBean(ref);
                        }
                        if (injectValue != null) {
                            method.invoke(bean, injectValue);
                        }
                        break;
                    }
                }

            }
        } catch (Exception e) {
            log.error("populateBean 失败", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public Object getSingletonBean(String name) {
        return singletonObjects.get(name);
    }

    @Override
    public void registerSingleton(String name, Object bean) {
        earlySingletonObjects.remove(name);
        singletonObjects.put(name, bean);
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }

    public void registerEarlySingleton(String name, Object bean) {
        earlySingletonObjects.put(name, bean);
        singletonFactories.remove(name);
    }

    private Object getEarlySingleton(String name) {
        return earlySingletonObjects.get(name);
    }

    public void registerSingletonFactory(String name, ObjectFactory<?> singletonFactory) {
        singletonFactories.put(name, singletonFactory);
    }

    public Object getFactoryBean(String name) throws Exception {
        ObjectFactory<?> singletonFactory = singletonFactories.get(name);
        if (singletonFactory != null) {
            return singletonFactory.getObject();
        }
        return null;
    }

}
