package com.fintech.gams.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 基础配置
 *
 * @author Playguy
 */
@Data
@Configuration
@ConfigurationProperties("com.gams")
public class GlobalProperies
{
    private String  projectName   = "gams";
    
    private String  domainName    = "dev.com";
    
    private String  emailProvider = "generic";
    
    private String  cookiePath    = "/";
    
    private Boolean cookieSecure  = false;
    
    private String  tradeUrl      = "";
    
    private String  timeFormat    = "yyyy-MM-dd HH:mm:ss";
    
    private String  passWord      = "123456";
    
    private boolean openLog       = false;
    
    private boolean switchRemind  = false;
    
    private boolean esEnable      = false;
    
    private String  running       = "dev";
    
    /**** 跨域设置 ****/
    private String  allowOrigin   = "*";
    
    private String  allowMethods  = "POST,GET,OPTIONS,DELETE";
    
    private String  maxAge        = "3600";
    
    private Aliyun  aliyun        = new Aliyun();
    
    private Amazon  amazon        = new Amazon();

}
