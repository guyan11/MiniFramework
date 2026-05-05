package com.guyan.service;

import com.guyan.ioc.annonation.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserServiceAopImpl implements UserServiceAop {
    @Override
    public void test() {
        log.info("user service aop impl running ........");
    }
}
