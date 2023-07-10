/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.core.schedule.task;

import com.tangv.common.util.jackson.JsonUtils;
import com.tangv.oms.api.order.OmsOrderService;
import com.tangv.oms.facade.domain.order.dto.OrderDownloadDto;
import com.tangv.oms.facade.domain.tasks.Task;
import com.tangv.oms.facade.domain.tasks.TaskInfo;
import com.tangv.oms.facade.domain.tasks.TaskResult;
import com.tangv.oms.facade.domain.tasks.UploadOssResult;
import com.tangv.oms.facade.enums.tasks.TaskStatusEnum;
import com.tangv.oms.facade.enums.tasks.TaskTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author tang wei
 * @version OrderDownloadTask.java, v 0.1 2023/6/28 17:47 tang wei Exp $
 */
@Component
public class OrderDownloadTask implements Task {

    @Resource
    private OmsOrderService omsOrderService;

    @Override
    public TaskTypeEnum taskType() {
        return TaskTypeEnum.DOWNLOAD_ORDER;
    }

    @Override
    public TaskResult excute(TaskInfo taskInfo) {
        OrderDownloadDto orderDownloadDto = JsonUtils.parseObject(taskInfo.getParam(), OrderDownloadDto.class);
        //1.查询订单数据，需要分页查询，考虑是否可以分批上传，考虑是否可以多线程查询
        //2.
        TaskStatusEnum taskStatus = TaskStatusEnum.FAILED;
        UploadOssResult uploadOssResult = omsOrderService.downloadOrder(orderDownloadDto);
        if (uploadOssResult.getIsSuccess()) {
            taskStatus = TaskStatusEnum.SUCCESS;
        }
        TaskResult taskResult = new TaskResult();
        taskResult.setTaskResultInfo(uploadOssResult);
        taskResult.setTaskStatus(taskStatus);
        return taskResult;
    }
}
