/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.api.order;

import com.tangv.oms.facade.domain.tasks.TaskInfo;
import com.tangv.oms.facade.domain.tasks.TaskUpdateDto;

import java.util.List;

/**
 * @author tang wei
 * @version TStreamTaskService.java, v 0.1 2023/7/3 18:27 tang wei Exp $
 */
public interface TStreamTaskService {

    List<TaskInfo> findWaitTasks(List<String> taskTypes, int limit, boolean locked);

    int updateStatusByIds(List<Long> taskIdList, Integer prevStatus, Integer targetStatus);

    int updateStatusById(TaskUpdateDto taskUpdateDto);

}
