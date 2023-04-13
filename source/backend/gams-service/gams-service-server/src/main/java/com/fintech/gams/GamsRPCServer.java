package com.fintech.gams;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alipay.sofa.rpc.common.RpcOptions;
import com.fintech.gams.redis.RedisProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * GamsRPCServer
 * <p>File: GamsRPCServer.java </p>
 * <p>Title: GamsRPCServer </p>
 * <p>Description: GamsRPCServer </p>
 * <p>Copyright: Copyright (c) 2019-05-17</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Configuration
@EnableCaching
@EnableAsync
@EnableTransactionManagement
@ComponentScan("com.fintech")
@ImportResource({"classpath*:sofa-provider.xml"})
@EnableConfigurationProperties(RedisProperties.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
public class GamsRPCServer
{
    public static void main(String[] args)
    {
        // 禁用链路跟踪日志
        System.setProperty(RpcOptions.DEFAULT_TRACER, "");
        SpringApplication springApplication = new SpringApplication(GamsRPCServer.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
