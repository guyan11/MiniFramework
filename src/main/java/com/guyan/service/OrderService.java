package com.guyan.service;

import com.guyan.ioc.annonation.Component;

@Component
public class OrderService {
    public void hello() {
        System.out.println("hello order");
    }
}