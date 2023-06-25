package com.example.demo.controller;

import com.example.demo.entity.ErrorMessage;
import com.example.demo.exceptions.AlreadyPresentDetailException;
import com.example.demo.exceptions.PasswordAndConfirmPasswordExceptions;
import com.example.demo.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyPresentDetailException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessage alreadyPresentDetailException(AlreadyPresentDetailException alreadyPresentDetailException){
        return new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(),
                new Date(),
                alreadyPresentDetailException.getMessage(),
                "There is already present data in the DB"
                );
    }


    @ExceptionHandler(value = PasswordAndConfirmPasswordExceptions.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage passwordAndConfirmPasswordException(PasswordAndConfirmPasswordExceptions passwordAndConfirmPasswordExceptions){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                passwordAndConfirmPasswordExceptions.getMessage(),
                "Password and Confirm password don't match"
                );
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage userNotFoundException(UserNotFoundException userNotFoundException){
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                new Date(),
                userNotFoundException.getMessage(),
                "USER IS NOT FOUND"
        );
    }


}
