package com.fintech.gams.ib.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.ib.gateway")
public class IBGateWay
{
    private String  host;
    
    private Integer port;
    
    private Integer clientId;
}
