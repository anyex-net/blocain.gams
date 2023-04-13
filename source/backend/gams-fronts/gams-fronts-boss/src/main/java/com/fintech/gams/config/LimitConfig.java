package com.fintech.gams.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fintech.gams.interceptor.IPLimitHandlerInterceptor;

@Configuration
public class LimitConfig implements WebMvcConfigurer
{
    @Autowired
    private IPLimitHandlerInterceptor limitHandlerInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(limitHandlerInterceptor)//
                .addPathPatterns("/**") //
                .excludePathPatterns( //
                        "/error/**", //
                        "/static/**", //
                        "/webjars/**", //
                        "/v2/api-docs", //
                        "/swagger/**", //
                        "/swagger-ui.html", //
                        "/swagger-resources/**", //
                        "/scripts/**", //
                        "/styles/**", //
                        "/images/**"//
                );
    }
}
