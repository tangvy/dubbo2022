/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.core.controller;

import com.tangv.oms.core.schedule.task.TaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang wei
 * @version TaskController.java, v 0.1 2023/7/4 10:45 tang wei Exp $
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private TaskExecutor taskExecutor;


    @PostMapping("")
    public void excute(@RequestBody List<String> taskTypes) {
        taskExecutor.excute(taskTypes);
    }
}
