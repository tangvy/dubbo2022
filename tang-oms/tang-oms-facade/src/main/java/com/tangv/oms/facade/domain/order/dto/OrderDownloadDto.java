/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.facade.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tangv.common.base.entity.PageDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tang wei
 * @version OrderDownloadDto.java, v 0.1 2023/6/28 17:58 tang wei Exp $
 */
@Data
public class OrderDownloadDto implements Serializable {

    @JsonProperty("owner_code")
    private String ownerCode;

    /**
     * 1.直接全量导出
     * 2.分页导出
     * 3.分页并发导出
     */
    private Integer type;

}
