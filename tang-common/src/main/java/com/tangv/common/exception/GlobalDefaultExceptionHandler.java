package com.tangv.common.exception;

import com.tangv.common.response.Response;
import net.sf.jsqlparser.util.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * 统一异常处理
 *
 * @author tangwei
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalDefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handlerNoHandlerFoundException(NoHandlerFoundException e) {
        outPutErrorWarn(NoHandlerFoundException.class, SystemState.NOT_FOUND, e);
        return Response.error(SystemState.NOT_FOUND);
    }

    /**
     * HttpRequestMethodNotSupportedException 405 异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        outPutErrorWarn(HttpRequestMethodNotSupportedException.class, SystemState.METHOD_NOT_ALLOWED, e);
        return Response.error(SystemState.METHOD_NOT_ALLOWED);
    }

    /**
     * HttpMediaTypeNotSupportedException 415 异常处理
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Response handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        outPutErrorWarn(HttpMediaTypeNotSupportedException.class, SystemState.UNSUPPORTED_MEDIA_TYPE, e);
        return Response.error(SystemState.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * 参数校验错误
     */
    /*@ExceptionHandler(ValidationException.class)
    public Response handleValidationException(ValidationException e) {
        outPutError(HttpMessageNotReadableException.class, SystemState.PARAM_ERROR, e);
        String msg = String.format("%s : 错误详情( %s )", SystemState.PARAM_ERROR.message(), e.getMessage());
        return Response.error(SystemState.PARAM_ERROR.code(), msg);
    }*/

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Response handlerIllegalArgumentException(IllegalArgumentException e) {
        outPutError(IllegalArgumentException.class, SystemState.BUSINESS_ERROR, e);
        return Response.error(SystemState.PARAM_ERROR.code(), e.getMessage());
    }

    /**
     * 类捕获 500 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public Response handlerException(Exception e) {
        return ifDepthExceptionType(e);
    }

    /**
     * 二次深度检查错误类型
     */
    private Response ifDepthExceptionType(Throwable throwable) {
        outPutError(Exception.class, SystemState.ERROR, throwable);
        return Response.error(SystemState.ERROR);
    }

    /**
     * BusinessException 类捕获
     */
    @ExceptionHandler(value = BusinessException.class)
    public Response handlerBusinessException(BusinessException e) {
        outPutError(SystemState.class, SystemState.BUSINESS_ERROR, e);
        return Response.error(e.getState());
    }

    /**
     * HttpMessageNotReadableException 参数错误异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        outPutError(HttpMessageNotReadableException.class, SystemState.PARAM_ERROR, e);
        String msg = String.format("%s : 错误详情( %s )", SystemState.PARAM_ERROR.message(), e.getMessage());
        return Response.error(SystemState.PARAM_ERROR.code(), msg);
    }

    /**
     * BindException 参数错误异常
     */
    @ExceptionHandler(BindException.class)
    public Response handleBindException(BindException e) {
        outPutError(BindException.class, SystemState.PARAM_ERROR, e);
        BindingResult bindingResponse = e.getBindingResult();
        return getBindResponseDTO(bindingResponse);
    }

    /**
     * MethodArgumentNotValidException 参数验证错误异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        outPutError(BindException.class, SystemState.PARAM_ERROR, e);
        BindingResult bindingResponse = e.getBindingResult();
        return getBindResponseDTO(bindingResponse);
    }

    private Response getBindResponseDTO(BindingResult bindingResponse) {
        List<FieldError> fieldErrors = bindingResponse.getFieldErrors();
        if (logger.isDebugEnabled()) {
            for (FieldError error : fieldErrors) {
                logger.error("{} -> {}", error.getDefaultMessage(), error.getDefaultMessage());
            }
        }

        if (fieldErrors.isEmpty()) {
            logger.error("validExceptionHandler error fieldErrors is empty");
            return Response.error(SystemState.BUSINESS_ERROR.code(), "");
        }
        return Response.error(SystemState.PARAM_ERROR.code(), fieldErrors.get(0).getDefaultMessage());
    }

    private void outPutError(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        logger.error( errorType.getSimpleName()+":"+ secondaryErrorType+":"+throwable.getMessage()+",堆栈=",throwable);
    }

    private void outPutErrorWarn(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        logger.warn( errorType.getSimpleName()+":"+ secondaryErrorType+":"+throwable.getMessage()+",堆栈=",throwable);
    }
}
