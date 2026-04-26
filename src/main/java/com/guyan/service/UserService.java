package com.guyan.service;


import com.guyan.ioc.annonation.Autowired;
import com.guyan.ioc.annonation.Component;
import com.guyan.ioc.domain.User;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Slf4j
@Component
public class UserService {

    @Autowired
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
