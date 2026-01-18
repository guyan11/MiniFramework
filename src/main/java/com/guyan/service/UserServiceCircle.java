package com.guyan.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class UserServiceCircle {

    private OrderServiceCircle orderServiceCircle;

    public void circle() {
        log.info("hello user circle");
    }
}
