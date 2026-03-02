package com.guyan.ioc.core;

public class XmlApplicationContext implements ApplicationContext {

    private final DefaultBeanFactory beanFactory;

    public XmlApplicationContext(String location) throws Exception {
        this.beanFactory = new DefaultBeanFactory();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(location);
    }

    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    public DefaultBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
