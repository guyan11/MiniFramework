package com.guyan.ioc.exception;

public class MessageException extends RuntimeException{

    private String message;

    private String code;

    public MessageException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
