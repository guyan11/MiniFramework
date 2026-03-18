package com.guyan.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceAopImpl implements UserServiceAop {
    @Override
    public void test() {
        log.info("user service aop impl running ........");
    }
}
