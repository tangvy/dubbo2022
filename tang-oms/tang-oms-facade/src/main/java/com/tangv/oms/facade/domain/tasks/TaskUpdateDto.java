/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.facade.domain.tasks;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tang wei
 * @version TaskUpdateDto.java, v 0.1 2023/7/4 16:18 tang wei Exp $
 */
@Data
public class TaskUpdateDto implements Serializable {

    private Long taskId;

    private Integer targetStatus;

    private String remark;

    private Double cost;

    private Date nextRequestTime;

    private Integer version;
}
