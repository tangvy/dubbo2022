/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 *//*

package com.tangv.common.concurrent;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.xml.crypto.Data;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

*/
/**
 * @author tang wei
 * @version TransactionExecutor.java, v 0.1 2023/4/22 19:05 tang wei Exp $
 *//*

public class TransactionExecutor {

    private static Integer taskCount;

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static CountDownLatch taskCountDownLatch = new CountDownLatch(taskCount);

    private static CountDownLatch rollbackCountDownLatch = new CountDownLatch(1);

    private static AtomicBoolean rollbackFlag = new AtomicBoolean(false);

    private static DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();

    private static DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();

    private static TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);


    public TransactionExecutor(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public void execute(Supplier supplier) {
        Runnable runnable = new TransactionRunnable(supplier, taskCountDownLatch, rollbackCountDownLatch, rollbackFlag, transactionManager, transactionStatus);
        executorService.execute(runnable);
    }

    public static void main(String[] args) throws InterruptedException {
        TransactionExecutor transactionExecutor = new TransactionExecutor(5);
        for (int i = 0;i < 10;i++) {
            transactionExecutor.execute(() -> {
                System.out.println(1);
                return null;
            });
        }
    }
}
*/
