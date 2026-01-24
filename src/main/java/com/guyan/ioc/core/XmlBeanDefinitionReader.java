package com.guyan.ioc.core;

import com.guyan.ioc.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

@Slf4j
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry beanFactoryRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanFactoryRegistry) {
        this.beanFactoryRegistry = beanFactoryRegistry;
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {

        // 解析 XMl 文件
        log.info("开始加载 xml 文件: {}", location);
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
        if (inputStream == null) {
            log.error("xml 文件不存在: {}", location);
            throw new Exception("xml 文件不存在: " + location);
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
                    Object propertyValue;
                    if (StringUtil.isNotEmpty(value)) {
                        propertyValue = value;
                        beanDefinition.getPropertyValues().add(new PropertyValue(name, propertyValue));
                    } else {
                        propertyValue = new BeanReference(ref);
                        beanDefinition.getPropertyValues().add(new PropertyValue(name, propertyValue));
                    }
                }
            }
            beanFactoryRegistry.registerBeanDefinition(id, beanDefinition);
        }
    }
}
