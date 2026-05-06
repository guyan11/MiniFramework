package com.guyan.service;

import com.guyan.ioc.annonation.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserServiceAopImpl implements UserServiceAop {


    private final ConstructorService constructorService;

    public UserServiceAopImpl(ConstructorService constructorService) {
        this.constructorService = constructorService;
    }

    @Override
    public void test() {
        constructorService.hello();
        log.info("user service aop impl running ........");
    }
}
