package com.tangv.notify.core;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * author:   tangwei
 * Date:     2020/12/23 11:39
 * Description:
 */
@EnableDubbo
@SpringBootApplication
@MapperScan("com.tangv.notify.core.dao")
@ImportAutoConfiguration({DruidDataSourceAutoConfigure.class})
public class NotifyCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotifyCoreApplication.class,args);
    }
}