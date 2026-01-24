package com.guyan.service;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString(exclude = "userServiceCircle")
public class OrderServiceCircle {

    private UserServiceCircle userServiceCircle;

    public void circle() {
        log.info("hello order circle");
    }
}
