package com.tangv.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author weishili
 */
public class BusinessException extends RuntimeException {

    private ResultState state;

    private BusinessException() {
    }

    public BusinessException(ResultState state) {
        super(state.message());
        this.state = state;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.state = new ExceptionState(code, message, HttpStatus.BAD_REQUEST);
    }

    public ResultState getState() {
        return state;
    }


    private class ExceptionState implements ResultState {

        private int code;

        private String message;

        private HttpStatus httpStatus;

        public ExceptionState(int code, String message, HttpStatus httpStatus) {
            this.code = code;
            this.message = message;
            this.httpStatus = httpStatus;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String message() {
            return this.message;
        }

        @Override
        public HttpStatus httpStatus() {
            return this.httpStatus;
        }
    }

}