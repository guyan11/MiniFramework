package com.guyan.ioc;

import com.guyan.ioc.core.BeanDefinition;
import com.guyan.ioc.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MiniApplicationContext {

    // 存 BeanDefinition
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    // 存 Bean 实例（单例池）
    private final Map<String, Object> singletonObjects = new HashMap<>();

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
            if (StringUtil.isEmpty(id)) {
                log.warn("bean 定义中没有 id 属性");
                continue;
            }
            String className = element.getAttribute("class");

            BeanDefinition beanDefinition = new BeanDefinition(id, className);
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
    }

}
