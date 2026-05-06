package com.guyan.service;

import com.guyan.ioc.annonation.Component;

@Component
public class ConstructorService {

    private final UserServiceAop userServiceAop;

    public ConstructorService(UserServiceAop userServiceAop) {
        this.userServiceAop = userServiceAop;
    }

    public void hello() {
        userServiceAop.test();
        System.out.println("hello constructor");
    }
}
