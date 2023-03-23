package com.tangv.feature;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * author:   tangwei
 * Date:     2020/12/23 11:39
 * Description:
 */
@SpringBootApplication
@MapperScan("com.tangv.feature.dao")
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class,args);
    }
}