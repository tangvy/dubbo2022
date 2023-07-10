/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.facade.domain.tasks;

import com.tangv.oms.facade.enums.tasks.TaskStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tang wei
 * @version TaskResult.java, v 0.1 2023/7/10 12:10 tang wei Exp $
 */
@Data
public class TaskResult implements Serializable {

    /**
     * 任务状态结果
     */
    private TaskStatusEnum taskStatus;

    /**
     * 任务结果回传的信息
     */
    private TaskResultInfo taskResultInfo;

}
