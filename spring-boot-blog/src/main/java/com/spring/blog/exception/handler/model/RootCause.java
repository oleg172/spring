package com.spring.blog.exception.handler.model;

public enum RootCause {

    NAME_CONSTRAINT("name constraint failed"),
    EMAIL_CONSTRAINT("e-mail constraint failed"),
    UNKNOWN_CONSTRAINT("unknown failed");

    private String value;

    private RootCause(String s) {
        value = s;
    }
}
