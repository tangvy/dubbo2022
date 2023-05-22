package com.tangv.uaa.core.config.mysql;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.tangv.common.enums.DataBaseType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * author:   tangwei
 * Date:     2020/12/30 14:52
 * Description: 数据源配置
 */

@Configuration
/**
 * 关闭springboot数据源自动配置，否则出现异常The dependencies of some of the beans in the application context form a cycle:
 */
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@MapperScan(value = "com.tangv.**.dao.**", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    /**
     * 数据源:mysql数据库canal_tangv
     */
    @Bean(name = "tangUaa")
    @ConfigurationProperties(prefix = "spring.datasource.druid.tang-uaa")
    public DataSource tangUaa() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 自定义数据源DynamicDataSource，继承AbstractRoutingDataSource，把多个数据源管理起来
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(@Qualifier(value = "tangUaa") DataSource tangUaa) {
        Map<Object, Object> targetDataSources = new HashMap<>(1);
        targetDataSources.put(DataBaseType.CANAL_TANGV,tangUaa);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(tangUaa);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    /**
     * SQLSession工厂
     */
    @Bean(name = "sqlSessionFactoryBean")
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean;
    }

    /**
     * 配置一个可以进行批量执行的sqlSession
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryBean") MybatisSqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject(), ExecutorType.SIMPLE);
        return sqlSessionTemplate;
    }

    /**
     * spring事务管理
     */
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dynamicDataSource);
        return transactionManager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(SqlSessionFactory sqlSessionFactory, @Qualifier("mybatisSqlLogInterceptor") Interceptor mybatisSqlLogInterceptor) {
        sqlSessionFactory.getConfiguration().addInterceptor(mybatisSqlLogInterceptor);
        return sqlSessionFactory;
    }

    @Bean(name = "mybatisSqlLogInterceptor")
    public Interceptor mybatisSqlLogInterceptor() {
        return new MybatisSqlLogInterceptor();
    }
}