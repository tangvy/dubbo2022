package com.tangv.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author weishili
 */
public interface ResultState {

    int code();

    String message();

    HttpStatus httpStatus();
}
