/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.core.schedule.task;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.tangv.oms.api.order.TStreamTaskService;
import com.tangv.oms.facade.domain.tasks.Task;
import com.tangv.oms.facade.domain.tasks.TaskInfo;
import com.tangv.oms.facade.domain.tasks.TaskUpdateDto;
import com.tangv.oms.facade.enums.tasks.TaskStatusEnum;
import com.tangv.oms.facade.enums.tasks.TaskTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author tang wei
 * @version TaskExecutor.java, v 0.1 2023/7/3 18:02 tang wei Exp $
 */
@Slf4j
@Component
public class TaskExecutor implements ApplicationContextAware {

    private static final int MAX_RETRY = 3;

    private static final int BASE = 2;

    private static final int BASE_SECOND = 120*1000;
    
    @Resource
    private TStreamTaskService tStreamTaskService;

    @Resource
    private TransactionTemplate transactionTemplate;

    Map<TaskTypeEnum, Task> taskContainer = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Task> taskMap = applicationContext.getBeansOfType(Task.class);
        for (String key : taskMap.keySet()) {
            Task task = taskMap.get(key);
            taskContainer.put(task.taskType(), task);
        }
    }

    public void excute(List<String> taskTypes) {
        //1.查询待执行的任务
        List<TaskInfo> taskList = transactionTemplate.execute(status -> {
            //1.1.加锁查询出等待执行的任务
            List<TaskInfo> waitTasks = tStreamTaskService.findWaitTasks(taskTypes, 10, true);
            if (CollectionUtil.isEmpty(waitTasks)) {
                return waitTasks;
            }
            List<Long> taskIdList = waitTasks.stream().map(TaskInfo::getTaskId).collect(Collectors.toList());
            //1.2.将任务更新为执行中
            tStreamTaskService.updateStatusByIds(taskIdList, TaskStatusEnum.DOING.getCode(),
                    TaskStatusEnum.WAIT.getCode());
            return waitTasks;
        });

        for (TaskInfo taskInfo : taskList) {
            //2.从容器中获取具体执行任务的bean
            TaskTypeEnum taskTypeEnum = TaskTypeEnum.getByCode(taskInfo.getTaskType());
            Task task = taskContainer.get(taskTypeEnum);
            excuteTask(task, taskInfo);
        }
    }

    private void excuteTask(Task task, TaskInfo taskInfo) {
        TaskStatusEnum status = null;
        String remark = "SUCCESS";
        Date nextRequestTime = new Date();
        StopWatch stopWatch = new StopWatch(taskInfo.getTaskId().toString());
        stopWatch.start();
        try {
            status = task.excute(taskInfo);
        } catch (Exception e) {
            //计算下次重试时间
            long delay = (long) Math.pow(BASE, taskInfo.getVersion()) * BASE_SECOND;
            nextRequestTime = new Date(nextRequestTime.getTime() + delay);
            //单个任务执行异常并且达到最大重试次数，则设置为执行失败
            if (taskInfo.getVersion() >= MAX_RETRY) {
                status = TaskStatusEnum.FAILED;
            } else {
                //单个任务执行异常则设置为等待中
                status = TaskStatusEnum.WAIT;
            }
            remark = getWriteLogByException(e);
            e.printStackTrace();
        } finally {
            stopWatch.stop();
            double cost = stopWatch.getTotalTimeSeconds();
            log.info("任务{}, 耗时：{}", taskInfo.getTaskId(), cost);
            //更新任务状态
            TaskUpdateDto taskUpdateDto = new TaskUpdateDto();
            taskUpdateDto.setTaskId(taskInfo.getTaskId());
            taskUpdateDto.setTargetStatus(status.getCode());
            taskUpdateDto.setRemark(remark);
            taskUpdateDto.setCost(cost);
            taskUpdateDto.setVersion(taskInfo.getVersion() + 1);
            taskUpdateDto.setNextRequestTime(nextRequestTime);
            tStreamTaskService.updateStatusById(taskUpdateDto);
        }
    }

    /**
     * 根据异常对象获取需要记录的log信息
     *
     * @param e
     * @return
     */
    private String getWriteLogByException(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String remark = sw.getBuffer().toString();
        return (StrUtil.isEmpty(remark) || remark.length() < 1000) ? remark : remark.substring(0, 1000);
    }
}
