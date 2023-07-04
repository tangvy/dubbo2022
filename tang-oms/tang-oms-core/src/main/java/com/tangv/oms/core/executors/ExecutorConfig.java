/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.core.executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tang wei
 * @version ExecutorConfig.java, v 0.1 2023/7/4 11:16 tang wei Exp $
 */
@Configuration
public class ExecutorConfig {

    @Bean(name = "taskPoolExecutor")
    public ExecutorService taskExecutor() {
        ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(4));
        return taskExecutor;
    }
}
