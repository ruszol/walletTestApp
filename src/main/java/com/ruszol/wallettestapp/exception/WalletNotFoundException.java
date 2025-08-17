package com.ruszol.wallettestapp.exception;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
