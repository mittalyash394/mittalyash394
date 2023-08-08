package com.example.demo.exceptions;

public class LoginPayloadExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;


    public LoginPayloadExceptions(String message){
        super(message);
    }

}
