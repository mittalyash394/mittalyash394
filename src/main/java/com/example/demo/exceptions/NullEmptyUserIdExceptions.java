package com.example.demo.exceptions;

public class NullEmptyUserIdExceptions extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NullEmptyUserIdExceptions(String message) {
        super(message);
    }

}
