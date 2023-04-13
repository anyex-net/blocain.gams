package com.fintech.gams.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.gams.oauth2")
public class Oauth2Config
{
    private String clientId;
    
    private String clientSecret;
    
    private String redirectUrl;
    
    private String userInfoUrl;
    
    private String accessTokenUrl;
    
    private String authorizeUrl;
    
    private String successUrl;
    
    private String failureUrl;
    
    private String accessLogoutUrl;
}
