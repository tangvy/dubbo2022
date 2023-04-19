package com.tangv.common.response;

import com.tangv.common.exception.ResultState;
import org.springframework.http.HttpStatus;

/**
 * author:   tangwei
 * Date:     2020/12/25 14:46
 * Description:
 */
public class Response<T> {

    private T data;

    private Integer code;

    private String message;

    private Integer httpCode;

    private String httpMessage;

    public Response(T data) {
        this.data = data;
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
        this.httpCode = HttpStatus.OK.value();
        this.httpMessage = HttpStatus.OK.getReasonPhrase();
    }

    public Response(T data, Integer code, String message, HttpStatus httpStatus) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.httpCode = httpStatus.value();
        this.httpMessage = httpStatus.OK.getReasonPhrase();
    }

    public Response(ResultState state, final T data) {
        this.code = state.code();
        this.message = state.message();
        this.httpCode = state.httpStatus().value();
        this.httpMessage = state.httpStatus().getReasonPhrase();
        this.data = data;
    }

    public Response() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
        this.httpCode = HttpStatus.OK.value();
        this.httpMessage = HttpStatus.OK.getReasonPhrase();
    }

    public static <T> Response<T> success() {
        return new Response();
    }

    public static <T> Response<T> success(T data) {
        return new Response(data);
    }

    public static <T> Response<T> error() {
        return new Response(null, ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> Response<T> error(ResultState state) {
        Response response = new Response(state, null);
        return response;
    }

    public static <T> Response<T> error(int code, String msg) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public void setHttpMessage(String httpMessage) {
        this.httpMessage = httpMessage;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }
}