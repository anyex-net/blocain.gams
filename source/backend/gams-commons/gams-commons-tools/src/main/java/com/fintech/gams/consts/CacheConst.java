package com.fintech.gams.consts;

/**
 * <p>File：CacheConst.java </p>
 * <p>Title: 缓存前缀声明 </p>
 * <p>Description: CacheConst </p>
 * <p>Copyright: Copyright (c) 15/9/1</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class CacheConst
{
    private CacheConst()
    {
    }
    
    /**
     * 一秒
     */
    public static final Integer ONE_SECONDS_CACHE_TIME     = 1;
    
    /**
     * 两秒
     */
    public static final Integer TWO_SECONDS_CACHE_TIME     = 2;
    
    /**
     * 三秒
     */
    public static final Integer THREE_SECONDS_CACHE_TIME   = 3;
    
    /**
     * 60秒
     */
    public static final Integer ONE_MINUTE_CACHE_TIME      = 60;
    
    /**
     * APP参数默认缓存时间(5分钟)
     */
    public static final Integer DEFAULT_CACHE_TIME         = 300;
    
    /**
     * 15分钟
     */
    public static final Integer FIFTEEN_MINUTE_CACHE_TIME  = 900;
    
    /**
     * 30分钟
     */
    public static final Integer THIRTY_MINUTE_CACHE_TIME   = 1800;
    
    /**
     * 60分钟
     */
    public static final Integer ONE_HOUR_CACHE_TIME        = 3600;
    
    /**
     * 24小时
     */
    public static final Integer TWENTYFOUR_HOUR_CACHE_TIME = 86400;
    
    /**
     * GOOGLE CODE
     */
    public static final String  GOOGLE_CODE_PERFIX         = "google:code";
    
    /**
     * 临时会话
     */
    public static final String  CACHE_CODE_PERFIX          = "cache:temp";
    
    /**
     * 消息发送
     */
    public static final String  CACHE_SEND_SMS_PERFIX      = "message:phone";
    
    /**
     * 消息邮件
     */
    public static final String  CACHE_SEND_EMAIL_PERFIX    = "message:email";
    
    /**
     * 消息过期
     */
    public static final String  CACHE_EXPIRE_PERFIX        = "message:expire";
    
    /**
     * 操作次数记数
     */
    public static final String  OPERATION_COUNT_PREFIX     = "operator:count";
    
    /**
     * 帐户锁定
     */
    public static final String  ACCOUNT_LOCK_PREFIX        = "account:lock";
    
    /**
     * 会话对象
     */
    public static final String  TRADE_SHIRO_CACHE_PREFIX   = "trade:session";
    
    /**
     * 会话对象
     */
    public static final String  BOSS_SHIRO_CACHE_PREFIX    = "boss:session";
    
    /**
     * 会话对象
     */
    public static final String  OAUTH_SHIRO_CACHE_PREFIX   = "oauth:session";
    
    /**
     * 锁标识
     */
    public static final String  LOCK_PERFIX                = "lock:";
    
    /**
     * 安全策略
     */
    public static final String  POLICY_PERFIX              = "policy:";
    
    /**
     * 登陆
     */
    public static final String  LOGIN_PERFIX               = "login:";
    
    /**
     * 安全重置密码
     */
    public static final String  RESET_SECURITY_PREFIX      = "security:reset";
    
    /**
     * 会话统计
     */
    public static final String  REDIS_SHIRO_SESSION_PREFIX = "shiro:statis";
    
    /**
     * 行情
     */
    public static final String  REDIS_QUOTATION_PREFIX     = "quotation";
    
    /**
     * 监控
     */
    public static final String  REDIS_MONITOR_PREFIX       = "monitor";
    
    /**
     * 平台变动扫描
     */
    public static final String  REDIS_PLATSCAN_PREFIX      = "platscan";
    
    /**
     * 外部网关IB
     */
    public static final String  REDIS_GATEWAY_IB_PREFIX    = "gateway:ib";


    /**
     * 账户资产充值入金锁前缀
     */
    public static final String  LOCK_ACCOUNT_ASSETDEPOSIT_PREFIX  = "lock:account:assetdeposit";

    /**
     * 账户资产提现出金锁前缀
     */
    public static final String  LOCK_ACCOUNT_ASSETWITHDRAW_PREFIX = "lock:account:assetwithdraw";
}
