/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.common.enums.binlogs;

import lombok.Setter;

/**
 * @author tang wei
 * @version BinlogOperationType.java, v 0.1 2023/3/2 15:45 tang wei Exp $
 */
public enum BinlogOperationTypeEnum {

    INSERT("INSERT"),

    UPDATE("UPDATE"),

    DELETE("DELETE"),

    ;

    BinlogOperationTypeEnum(String type) {
        this.type = type;
    }

    @Setter
    private String type;

    public static BinlogOperationTypeEnum getByType(String type) {
        for (BinlogOperationTypeEnum value : BinlogOperationTypeEnum.values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

}
