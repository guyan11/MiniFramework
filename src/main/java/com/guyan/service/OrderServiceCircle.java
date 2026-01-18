package com.guyan.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class OrderServiceCircle {

    private UserServiceCircle userServiceCircle;

    public void circle() {
        log.info("hello order circle");
    }
}
