package com.fintech.gams;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.alipay.sofa.rpc.common.RpcOptions;

/**
 * GamsBossServer
 * <p>File: GamsBossServer.java </p>
 * <p>Title: GamsBossServer </p>
 * <p>Description: GamsBossServer </p>
 * <p>Copyright: Copyright (c) 2019-05-17</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.fintech")
@ImportResource("classpath*:sofa-reference.xml")
@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class})
public class GamsBossServer
{
    public static void main(String[] args)
    {
        // 禁用链路跟踪日志
        System.setProperty(RpcOptions.DEFAULT_TRACER, "");
        SpringApplication springApplication = new SpringApplication(GamsBossServer.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}