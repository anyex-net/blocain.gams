package com.fintech.gams;

import com.alipay.sofa.rpc.common.RpcOptions;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = "com.fintech")
@ImportResource("classpath*:sofa-reference.xml")
@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class})
public class GamsAppServer {

    public static void main(String[] args) {
        // 禁用链路跟踪日志
        System.setProperty(RpcOptions.DEFAULT_TRACER, "");
        SpringApplication springApplication = new SpringApplication(GamsAppServer.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
