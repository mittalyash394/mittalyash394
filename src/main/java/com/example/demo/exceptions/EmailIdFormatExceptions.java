package com.example.demo.exceptions;

public class EmailIdFormatExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EmailIdFormatExceptions(String message){
        super(message);
    }

}
