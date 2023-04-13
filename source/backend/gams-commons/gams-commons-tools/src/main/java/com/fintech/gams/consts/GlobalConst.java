/*
 * @(#)ZttxConst.java 2015-4-14 下午2:02:23
 * Copyright 2015 Playguy, Inc. All rights reserved. com.fintech
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.consts;

/**
 * <p>File：GlobalConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-14 下午2:02:23</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class GlobalConst
{
    private GlobalConst()
    {// 防止实例化
    }
    
    /**
     * 默认语言
     */
    public static final String  DEFAULT_LANG                   = "en_US";
    
    /**
     * 中国电话代码
     */
    public static final String  CHINA_PHONE_CODE               = "86";
    
    public static final String  POST                           = "post";
    
    /**
     * 当前页面
     */
    public static final Integer DEFAULT_CURRENT_PAGE           = 1;
    
    /**
     * 分页大小
     */
    public static final Integer DEFAULT_PAGE_SIZE              = 10;
    
    /**
     * 分页起始大小
     */
    public static final Integer DEFAULT_START_INDEX            = 0;
    
    /**
     * 批处理大小
     */
    public static final Integer DEFAULT_BATCH_SIZE             = 100;
    
    /**
     * 默认限制次数
     */
    public static final Integer DEFAULT_REQUEST_LIMIT          = 10;
    
    /**
     * 分割符
     */
    public static final char    SEPARATOR                      = ':';
    
    /**
     * The default interval: 3000 ms = 3 seconds.
     */
    public static final long    DEFAULT_INTERVAL               = 3000;
    
    /**
     * 邮件推送服务标识
     */
    public static final String  EMAIL_PROVIDER_AMAZON          = "amazon";
    
    public static final String  EMAIL_PROVIDER_GENERIC         = "generic";
    
    /**
     * 确认状态
     */
    public static final String  STATUS_UNCONFIRMED             = "unconfirmed";
    
    public static final String  STATUS_CONFIRMED               = "confirmed";
    
    public static final String  STATUS_CANCELED                = "canceled";
    
    /**
     * 启用
     */
    public static final String  SWITCH_ENABLE                  = "enable";
    
    /**
     * 停用
     */
    public static final String  SWITCH_DISABLE                 = "disable";
    
    /**
     * OAUTH2.0
     */
    public static final String  BEARER_TYPE                    = "Bearer";
    
    /**
     * 默认的 refresh_token 的有效时长: 30天
     */
    public final static int     REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * 30;
    
    /**
     * 默认的 access_token 的有效时长: 12小时
     */
    public final static int     ACCESS_TOKEN_VALIDITY_SECONDS  = 60 * 60 * 12;
    
    public static final String  BOSS_SESSION_ID                = "bid";
    
    public static final String  TRADE_SESSION_ID               = "tid";
    
    public static final String  OAUTH_SESSION_ID               = "oid";
    
    public static final String  REQUEST_USERNAME               = "username";
    
    public static final String  REQUEST_PASSWORD               = "password";
    
    public static final String  REQUEST_POLICY_SMS             = "sms";
    
    public static final String  REQUEST_POLICY_GA              = "ga";
    
    public static final String  OAUTH_LOGIN_VIEW               = "oauth_login";
    
    public static final String  OAUTH_LOGIN2_VIEW              = "oauth_check";
    
    public static final String  OAUTH_APPROVAL_VIEW            = "oauth_approval";
    
    public static final String  REQUEST_USER_OAUTH_APPROVAL    = "user_oauth_approval";
    
    public static final String  RESOURCE_SERVER_NAME           = "oauth2-server";
    
    public static final String  INVALID_CLIENT_DESCRIPTION     = "客户端验证失败，如错误的client_id/client_secret";
    
    public static final String  INVALID_CODE_DESCRIPTION       = "错误的授权码";
    
    /**
     * 运行环境
     */
    public static final String  DEV                            = "dev";
    
    public static final String  PROD                           = "prod";
    
    /**
     * 模块
     */
    public static final String  SYSTEM                         = "/system";
    
    public static final String  COMMON                         = "/common";
    
    public static final String  ACCOUNT                        = "/account";
    
    public static final String  QUERY                          = "/query";
    
    public static final String  STOCK                          = "/stock";
    
    public static final String  QUOTE                          = "/quote";
    
    public static final String  REGISTER                       = "/register";
    
    public static final String  OPENACCOUNT                    = "/openAccount";
    
    public static final String  ASSET                          = "/asset";
    
    public static final String  IB                             = "/ib";
}
