package com.example.demo.exceptions;

public class EmptyUserIdExceptions extends RuntimeException{


    private static final long serialVersionUID = 1L;

    public EmptyUserIdExceptions(String message){
        super(message);
    }
}
