package com.example.demo.exceptions;

public class PasswordFormatExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public PasswordFormatExceptions(String message){
        super(message);
    }

}
