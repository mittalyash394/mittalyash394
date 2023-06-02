package com.example.demo.exceptions;

public class AlreadyPresentDetailException extends RuntimeException{

    public AlreadyPresentDetailException(String message){
        super(message);
    }

}
