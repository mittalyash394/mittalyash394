package com.example.demo.exceptions;

public class NullUserIdExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NullUserIdExceptions(String message){
        super(message);
    }

}
