/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.core.service.impl;

import com.tangv.oms.api.order.TStreamTaskService;
import com.tangv.oms.core.dao.TStreamTaskMapper;
import com.tangv.oms.facade.domain.order.vo.TStreamTaskVo;
import com.tangv.oms.facade.domain.tasks.TaskInfo;
import com.tangv.oms.facade.domain.tasks.TaskUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tang wei
 * @version TStreamTaskServiceImpl.java, v 0.1 2023/7/3 18:29 tang wei Exp $
 */
@Slf4j
@DubboService
public class TStreamTaskServiceImpl implements TStreamTaskService {

    @Resource
    private TStreamTaskMapper tStreamTaskMapper;

    @Override
    public List<TaskInfo> findWaitTasks(List<String> taskTypes, int limit, boolean locked) {
        List<TStreamTaskVo> waitTasks = tStreamTaskMapper.findWaitTasks(taskTypes, limit, locked);
        return waitTasks.stream().map(task -> {
            TaskInfo taskInfo = new TaskInfo();
            BeanUtils.copyProperties(task, taskInfo);
            return taskInfo;
        }).collect(Collectors.toList());
    }

    @Override
    public int updateStatusByIds(List<Long> taskIdList, Integer prevStatus, Integer targetStatus) {
        return tStreamTaskMapper.updateStatusByIds(taskIdList, prevStatus, targetStatus);
    }

    @Override
    public int updateStatusById(TaskUpdateDto taskUpdateDto) {
        return tStreamTaskMapper.updateStatusById(taskUpdateDto.getTaskId(), taskUpdateDto.getTargetStatus(),
                taskUpdateDto.getFileId(),
                taskUpdateDto.getRemark(), taskUpdateDto.getCost(), taskUpdateDto.getNextRequestTime(), taskUpdateDto.getVersion());
    }
}
