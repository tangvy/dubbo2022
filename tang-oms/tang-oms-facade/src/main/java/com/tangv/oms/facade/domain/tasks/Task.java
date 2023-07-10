/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.facade.domain.tasks;

import com.tangv.oms.facade.enums.tasks.TaskStatusEnum;
import com.tangv.oms.facade.enums.tasks.TaskTypeEnum;

/**
 * @author tang wei
 * @version Task.java, v 0.1 2023/6/28 17:25 tang wei Exp $
 */
public interface Task {

    TaskTypeEnum taskType();

    TaskResult excute(TaskInfo taskInfo);

}
