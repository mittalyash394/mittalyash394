package com.example.demo.exceptions;

public class PasswordAndConfirmPasswordExceptions extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PasswordAndConfirmPasswordExceptions(String passwordNotMatch){
        super(passwordNotMatch);
    }

}
