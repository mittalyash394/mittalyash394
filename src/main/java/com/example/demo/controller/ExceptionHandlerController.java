package com.example.demo.controller;

import com.example.demo.entity.ErrorMessage;
import com.example.demo.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
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

    @ExceptionHandler(value = NullUserIdExceptions.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage nullUserIdExceptions(NullUserIdExceptions nullUserIdExceptions){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                nullUserIdExceptions.getMessage(),
                "The userId cannot be null."
        );
    }


    @ExceptionHandler(value = EmptyUserIdExceptions.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage emptyUserIdExceptions(EmptyUserIdExceptions emptyUserIdExceptions){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                emptyUserIdExceptions.getMessage(),
                "The userId cannot be empty"
        );
    }

    @ExceptionHandler(value = UserIdPayloadExceptions.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage userIdPayloadExceptions(UserIdPayloadExceptions ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                "Something went wrong"
        );
    }

    @ExceptionHandler(value = LoginPayloadExceptions.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage loginPayloadExceptions(LoginPayloadExceptions ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                "Something went wrong"
        );
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage userIdPayloadExceptions(ConstraintViolationException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                "Something went wrong"
        );
    }


    @ExceptionHandler(value = RegisterUserPayloadExceptions.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage registerUserPayloadExceptions(RegisterUserPayloadExceptions ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                "Something went wrong"
        );
    }

    @ExceptionHandler(value = UpdatePasswordPayloadExceptions.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage updatePasswordPayloadExceptions(UpdatePasswordPayloadExceptions ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                "Something went wrong"
        );
    }

}
