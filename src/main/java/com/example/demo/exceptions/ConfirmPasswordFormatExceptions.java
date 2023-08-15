package com.example.demo.exceptions;

public class ConfirmPasswordFormatExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ConfirmPasswordFormatExceptions(String message){
        super(message);
    }

}
