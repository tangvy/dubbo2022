package com.tangv.common.exception;

import org.springframework.http.HttpStatus;

/**
 * tangwei
 */
public enum SystemState implements ResultState {

    /**
     * 404 Web 服务器找不到您所请求的文件或脚本。请检查URL 以确保路径正确。
     */
    NOT_FOUND(404, String.format("无法找到这个资源(%s)", HttpStatus.NOT_FOUND.getReasonPhrase()), HttpStatus.NOT_FOUND),

    /**
     * 405 对于请求所标识的资源，不允许使用请求行中所指定的方法。请确保为所请求的资源设置了正确的 MIME 类型。
     */
    METHOD_NOT_ALLOWED(405, String.format("请换个姿势操作试试(%s)", HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase()), HttpStatus.METHOD_NOT_ALLOWED),

    /**
     * 415 Unsupported Media Type
     */
    UNSUPPORTED_MEDIA_TYPE(415, String.format("不支持该媒体类型(%s)", HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase()), HttpStatus.UNSUPPORTED_MEDIA_TYPE),

    /**
     * 系统异常 500 服务器的内部错误
     */
    ERROR(500, "系统繁忙，请稍后再试", HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * 操作成功
     */
    SUCCESS(200, "success"),

    /**
     * 操作失败
     */
    FAILURE(401, "error"),

    /**
     * 服务调用异常
     */
    API_GATEWAY_ERROR(10001, "网络繁忙，请稍后再试"),

    /**
     * rpc调用异常
     */
    RPC_ERROR(10002, "网络出问题，请稍后再试"),

    /**
     * 系统限流
     */
    TRAFFIC_LIMITING(10003, "网络拥挤，请稍后再试"),

    /**
     * 服务降级
     */
    SERVICE_DEGRADE(10004, "服务不可用自动降级"),

    /**
     * 业务异常
     */
    BUSINESS_ERROR(20001, "业务异常"),

    /**
     * 参数错误
     */
    PARAM_ERROR(20002, "参数错误"),


    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;

    private SystemState(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    private SystemState(int code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = HttpStatus.BAD_REQUEST;
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
