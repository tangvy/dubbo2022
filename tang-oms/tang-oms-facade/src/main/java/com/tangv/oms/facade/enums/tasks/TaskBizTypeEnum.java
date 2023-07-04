/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.facade.enums.tasks;

/**
 * @author tang wei
 * @version TaskBizTypeEnum.java, v 0.1 2023/6/28 17:12 tang wei Exp $
 */
public enum TaskBizTypeEnum {

    ORDER("order", ""),

    ;

    TaskBizTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

}
