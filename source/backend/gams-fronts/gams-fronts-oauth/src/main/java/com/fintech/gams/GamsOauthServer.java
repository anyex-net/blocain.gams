package com.fintech.gams;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.alipay.sofa.rpc.common.RpcOptions;
import org.springframework.context.annotation.ImportResource;

/**
 * 统一认证中心
 * <p>File: GamsOauthServer.java </p>
 * <p>Title: GamsOauthServer </p>
 * <p>Description: GamsOauthServer </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.fintech")
@ImportResource("classpath*:sofa-reference.xml")
@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class})
public class GamsOauthServer
{
    public static void main(String[] args)
    {
        // 禁用链路跟踪日志
        System.setProperty(RpcOptions.DEFAULT_TRACER, "");
        SpringApplication springApplication = new SpringApplication(GamsOauthServer.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
