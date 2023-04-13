package com.fintech.gams;

import com.fintech.gams.redis.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * GamsIBServer
 * <p>File: GamsIBServer.java </p>
 * <p>Title: GamsIBServer </p>
 * <p>Description: GamsIBServer </p>
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
@EnableScheduling
@EnableTransactionManagement
@ComponentScan("com.fintech")
@EnableConfigurationProperties(RedisProperties.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
public class GamsIBServer
{
    // 解决使用@Scheduled创建任务时无法在同一时间执行多个任务的BUG
    // springboot创建的线程池poolSize确实是1，当前活动线程数(activethreads)为1
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(30);
        return taskScheduler;
    }

    public static void main(String[] args)
    {
        SpringApplication springApplication = new SpringApplication(GamsIBServer.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
