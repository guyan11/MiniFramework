package com.guyan.ioc;

import com.guyan.ioc.convert.TypeConverterFactory;
import com.guyan.ioc.core.BeanDefinition;
import com.guyan.ioc.core.PropertyValue;
import com.guyan.ioc.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MiniApplicationContext {

    // 存 BeanDefinition
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    // 存 Bean 实例（单例池）
    private final Map<String, Object> singletonObjects = new HashMap<>();

    private final TypeConverterFactory typeConverter = new TypeConverterFactory();

    public MiniApplicationContext(String xmlPath) throws Exception {
        loadBeans(xmlPath);
        createBean();
    }

    public Object getBean(String name) {
        return singletonObjects.get(name);
    }

    public void loadBeans(String xmlPath) throws Exception {
        // 解析 XMl 文件
        log.info("开始加载 xml 文件: {}", xmlPath);
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        if (inputStream == null) {
            log.error("xml 文件不存在: {}", xmlPath);
            throw new Exception("xml 文件不存在: " + xmlPath);
        }

        // 创建 Document 对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);

        // 获取根节点 <beans>
        Element root = document.getDocumentElement();

        NodeList bean = root.getElementsByTagName("bean");
        int length = bean.getLength();
        if (length <= 0) {
            log.warn("xml 文件中没有 bean 定义");
            throw new Exception("xml 文件中没有 bean 定义");
        }
        log.info("xml 文件中共找到 {} 个 bean 定义", length);

        for (int i = 0; i < length; i++) {
            Element element = (Element) bean.item(i);

            // 创建 BeanDefinition 对象
            String id = element.getAttribute("id");
            String className = element.getAttribute("class");

            boolean isEmpty = StringUtil.isEmpty(id) || StringUtil.isEmpty(className);
            if (isEmpty) {
                log.warn("bean 定义中没有 id 属性 或 bean 定义中没有 class 属性");
                continue;
            }


            BeanDefinition beanDefinition = new BeanDefinition(id, className);

            NodeList propertyNodes = element.getElementsByTagName("property");
            int propertyLength = propertyNodes.getLength();
            if (propertyLength > 0) {
                for (int j = 0; j < propertyLength; j++) {
                    Element propertyElement = (Element) propertyNodes.item(j);

                    String name = propertyElement.getAttribute("name");
                    String value = propertyElement.getAttribute("value");
                    String ref = propertyElement.getAttribute("ref");
                    boolean propertyEmpty = StringUtil.isEmpty(name) || (StringUtil.isEmpty(ref) && StringUtil.isEmpty(value));
                    if (propertyEmpty) {
                        log.warn("property 定义中没有 name 和 ref 或 value 属性");
                        continue;
                    }
                    if (StringUtil.isNotEmpty(value)) {
                        beanDefinition.getPropertyValues().add(new PropertyValue(name, value, null));
                    } else {
                        beanDefinition.getPropertyValues().add(new PropertyValue(name, null, ref));
                    }
                }
            }

            beanDefinitionMap.put(id, beanDefinition);
        }
    }

    public void createBean() throws Exception {

        if (beanDefinitionMap.isEmpty()) {
            log.warn("beanDefinitionMap 为空，无法创建 Bean");
            return;
        }

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanId = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();

            String className = beanDefinition.getClassName();
            Object obj = Class.forName(className).newInstance();

            singletonObjects.put(beanId, obj);
        }

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanId = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            Object bean = singletonObjects.get(beanId);
            populateProperties(bean, beanDefinition);
        }

    }

    public void populateProperties(Object bean, BeanDefinition beanDefinition) {
        boolean empty = beanDefinition.getPropertyValues().isEmpty();
        if (empty) {
            log.warn("beanDefinition 中没有 property 定义");
            return;
        }

        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues()) {
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
                            injectValue = singletonObjects.get(ref);
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

}
