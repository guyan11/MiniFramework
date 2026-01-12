package com.guyan.service;


import com.guyan.ioc.domain.User;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Slf4j
public class UserService {

    private OrderService orderService;

    private User user;

    public void hello() {
        System.out.println("hello user");
        orderService.hello();
    }

    public void user() {
        log.info("user={}", user);
    }


}
