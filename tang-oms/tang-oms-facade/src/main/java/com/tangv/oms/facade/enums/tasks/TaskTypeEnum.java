/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.facade.enums.tasks;

/**
 * @author tang wei
 * @version TaskTypeEnum.java, v 0.1 2023/6/28 17:12 tang wei Exp $
 */
public enum TaskTypeEnum {

    UPLOAD_ORDER("upload-order", ""),

    DOWNLOAD_ORDER("download-order", ""),

    ;

    TaskTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public static TaskTypeEnum getByCode(String code) {
        for (TaskTypeEnum value : TaskTypeEnum.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

}
