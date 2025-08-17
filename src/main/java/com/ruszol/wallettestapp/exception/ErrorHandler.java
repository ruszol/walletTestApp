package com.ruszol.wallettestapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(WalletNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleWalletNotFoundException(WalletNotFoundException e) {
        return new ErrorResponse(LocalDateTime.now(), e.getMessage());
    }


    @ExceptionHandler(InsufficentBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInsufficentBalanceException(InsufficentBalanceException e) {
        return new ErrorResponse(LocalDateTime.now(), e.getMessage());
    }

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidOperationException(InvalidOperationException e) {
        return new ErrorResponse(LocalDateTime.now(), e.getMessage());
    }



}
