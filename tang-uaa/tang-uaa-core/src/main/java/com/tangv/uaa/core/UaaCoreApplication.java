/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.core;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tang wei
 * @version UaaCoreApplication.java, v 0.1 2023/5/21 15:15 tang wei Exp $
 */
@EnableDubbo
@SpringBootApplication
@MapperScan("com.tangv.uaa.core.dao")
@ImportAutoConfiguration({DruidDataSourceAutoConfigure.class})
@ComponentScan(basePackages = "com.tangv")
public class UaaCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(UaaCoreApplication.class,args);
    }

}
