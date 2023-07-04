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
 * @version TaskInfo.java, v 0.1 2023/6/28 17:30 tang wei Exp $
 */
@Data
public class TaskInfo implements Serializable {

    private Long taskId;

    private String taskType;

    private String bizType;

    private String taskName;

    private String param;

    private Integer taskStatus;

    private String fileId;

    private String remark;

    private Date nextRequestTime;

    private Double costTime;

    private Integer version;

    private Boolean disabled;

    private Date createTime;

    private Date modifyTime;
}
