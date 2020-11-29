package com.harunugur.handle.exception;

public class DataNotFoundException extends RuntimeException {

    private String msg;

    public DataNotFoundException(String msg) {
        super(msg);
    }

}
