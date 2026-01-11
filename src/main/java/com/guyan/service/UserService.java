package com.guyan.service;


import lombok.Setter;

@Setter
public class UserService {

    private OrderService orderService;

    public void hello() {
        System.out.println("hello user");
        orderService.hello();
    }

}
