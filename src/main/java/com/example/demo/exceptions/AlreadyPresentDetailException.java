package com.example.demo.exceptions;

public class AlreadyPresentDetailException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public AlreadyPresentDetailException(String message){
        super(message);
    }

}
