package com.fintech.gams;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * GamsQuartzServer
 * <p>File: GamsQuartzServer.java </p>
 * <p>Title: GamsQuartzServer </p>
 * <p>Description: GamsQuartzServer </p>
 * <p>Copyright: Copyright (c) 2019-05-17</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Configuration
@EnableAsync
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = "com.fintech")
@ImportResource("classpath*:sofa-reference.xml")
public class GamsQuartzServer
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
        SpringApplication springApplication = new SpringApplication(GamsQuartzServer.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
