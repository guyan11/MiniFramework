package com.guyan.ioc;

import com.guyan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class MiniApplicationContextTest {

    @Test
    void getBean() {
        MiniApplicationContext context;
        try {
            context = new MiniApplicationContext("META-INF/beans.xml");
        } catch (Exception e) {
            log.error("加载 beans.xml 文件失败", e);
            throw new RuntimeException(e);
        }
        Object bean = context.getBean("userService");
        if (bean instanceof UserService) {
            UserService userService = (UserService) bean;
            userService.hello();
            userService.user();
        }
    }
}