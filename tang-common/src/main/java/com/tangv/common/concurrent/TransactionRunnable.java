/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 *//*

package com.tangv.common.concurrent;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.function.Supplier;

*/
/**
 * @author tang wei
 * @version TransactionRunnable.java, v 0.1 2023/4/22 19:03 tang wei Exp $
 *//*

public class TransactionRunnable implements Runnable {

    private CountDownLatch taskCountDownLatch;

    private CountDownLatch rollbackCountDownLatch;

    private AtomicBoolean rollbackFlag;

    private DataSourceTransactionManager transactionManager;

    private TransactionStatus transactionStatus;

    private Supplier supplier;

    public TransactionRunnable(Supplier supplier, CountDownLatch taskCountDownLatch, CountDownLatch rollbackCountDownLatch, AtomicBoolean rollbackFlag, DataSourceTransactionManager transactionManager, TransactionStatus transactionStatus) {
        this.supplier = supplier;
        this.taskCountDownLatch = taskCountDownLatch;
        this.rollbackCountDownLatch = rollbackCountDownLatch;
        this.rollbackFlag = rollbackFlag;
        this.transactionManager = transactionManager;
        this.transactionStatus = transactionStatus;
    }

    @Override
    public void run() {
        try {
            supplier.get();
            System.out.println("i");
            //如果执行没有异常，则无需回滚
            taskCountDownLatch.countDown();
            rollbackCountDownLatch.await();
            //如果有线程的事务回滚了，阻塞会结束，这时候根据rollbackFlag判断是不是需要回滚
            if (rollbackFlag.get()) {
                transactionManager.rollback(transactionStatus);
            } else {
                transactionManager.commit(transactionStatus);
            }
        } catch (Exception e) {
            //如果发生异常,设置回滚标记为true
            rollbackFlag.set(true);
            rollbackCountDownLatch.countDown();
        }
    }
}
*/
