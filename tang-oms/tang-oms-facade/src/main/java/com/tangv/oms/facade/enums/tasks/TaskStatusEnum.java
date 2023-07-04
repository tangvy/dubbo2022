/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.facade.enums.tasks;

import lombok.Getter;

/**
 * @author tang wei
 * @version TaskStatusEnum.java, v 0.1 2023/6/28 17:27 tang wei Exp $
 */
public enum TaskStatusEnum {

    WAIT(0, ""),

    DOING(100, ""),

    SUCCESS(200, ""),

    FAILED(500, ""),

    ;

    TaskStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    private Integer code;

    @Getter
    private String desc;
}
