package com.ruszol.wallettestapp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsufficentBalanceException extends RuntimeException {

    public InsufficentBalanceException(String message) {
        super(message);
        log.error(message);
    }
}
