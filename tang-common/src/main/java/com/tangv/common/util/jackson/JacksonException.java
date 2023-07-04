/**
 * Shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.common.util.jackson;

/**
 * jackson exception
 * <p>
 * author : daiwenkai
 * date: 2022/12/20 5:15 下午
 */
public class JacksonException extends RuntimeException {

    public JacksonException() {
        super();
    }

    public JacksonException(String message) {
        super(message);
    }

    public JacksonException(String message, Exception e) {
        super(message, e);
    }

    public JacksonException(Throwable cause) {
        super(cause);
    }
}
