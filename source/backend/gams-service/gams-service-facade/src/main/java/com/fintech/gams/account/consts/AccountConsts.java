package com.fintech.gams.account.consts;

/**
 * 帐户模块常量 介绍
 * <p>File：AccountConsts.java </p>
 * <p>Title: AccountConsts </p>
 * <p>Description:AccountConsts </p>
 * <p>Copyright: Copyright (c) 2017/7/10 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class AccountConsts
{
    private AccountConsts()
    {
    }
    
    // 正常
    public static final Integer ACCOUNT_STATUS_NORMAL           = 0;
    
    // 冻结
    public static final Integer ACCOUNT_STATUS_FROZEN           = 1;
    
    // 注销
    public static final Integer ACCOUNT_STATUS_CLOSE            = 2;

    // 开户第一页
    public static final Integer ACCOUNT_STATUS_PAGE_ONE         = -1;

    // 开户第二页
    public static final Integer ACCOUNT_STATUS_PAGE_TWO         = -2;

    // 开户第三页
    public static final Integer ACCOUNT_STATUS_PAGE_THREE       = -3;

    // 开户第四页
    public static final Integer ACCOUNT_STATUS_PAGE_FOUR        = -4;

    // 开户第五页
    public static final Integer ACCOUNT_STATUS_PAGE_FIVE        = -5;

    // 开户第六页
    public static final Integer ACCOUNT_STATUS_PAGE_SIX         = -6;

    // 开户第七页
    public static final Integer ACCOUNT_STATUS_PAGE_SEVEN       = -7;

    // 开户第八页
    public static final Integer ACCOUNT_STATUS_PAGE_EIGHT       = -8;

    // 开户第九页
    public static final Integer ACCOUNT_STATUS_PAGE_NINE        = -9;

    // 开户第十页
    public static final Integer ACCOUNT_STATUS_PAGE_TEN         = -10;

    // 开户第十一页
    public static final Integer ACCOUNT_STATUS_PAGE_ELEVEN      = -11;

    // 开户第十二页
    public static final Integer ACCOUNT_STATUS_PAGE_TWELVE      = -12;

    // 开户第十三页
    public static final Integer ACCOUNT_STATUS_PAGE_THIRTEEN    = -13;

    // 开户第十四页
    public static final Integer ACCOUNT_STATUS_PAGE_FOURTEEN    = -14;

    // 开户第十五页
    public static final Integer ACCOUNT_STATUS_PAGE_FIFTEEN     = -15;

    // 开户第十六页
    public static final Integer ACCOUNT_STATUS_PAGE_SIXTEEN     = -16;

    // 开户第十七页
    public static final Integer ACCOUNT_STATUS_PAGE_SEVENTEEN   = -17;

    // 开户第十八页
    public static final Integer ACCOUNT_STATUS_PAGE_EIGHTEEN    = -18;

    // 开户第十九页
    public static final Integer ACCOUNT_STATUS_PAGE_NINETEEN = -19;

    //  开户第十一页（两个页面 11a: -51  11b:-52）
    public static final Integer ACCOUNT_STATUS_PAGE_FIFTY_ONE = -51;

    // 开户第十一页（两个页面 11a: -51  11b:-52）
    public static final Integer ACCOUNT_STATUS_PAGE_FIFTY_TWO = -52;

    // 账户类型
    public static final String ACCOUNT_TYPE     = "accountType";

    // 有限公司
    public static final String ACCOUNT_LIMITEDLIABLITY     = "limitedLiablity";

    // 企业 （税收分类）
    public static final String ACCOUNT_ENTERPRISE     = "enterprise";

    // 合伙企业
    public static final String ACCOUNT_PARTNERSHIP     = "partnerShip";

    // 经营负责人
    public static final String ACCOUNT_OPERATIONPRINCIPAL   = "operationPrincipal";

    // 被授权人
    public static final String ACCOUNT_AUTHORIZEDPERSON   = "authorizedPerson";

    // 账户名
    public static final String ACCOUNT_NAME               = "accountName";

    // 手机号
    public static final String MOBILE                     = "mobile";

    // 邮箱
    public static final String EMAIL                      = "email";

    // 公司个人股东类型
    public static final String ACCOUNT_PERSONAL_TYPE    = "individualShareholder";

    // 法人股东
    public static final String LEGAL_PERSON_SHAREHOLDER    = "legalPersonShareholder";

    // 收入
    public static final String INCOME                      =  "income";

    // 交易利润
    public static final String TRADINGPROFITS              =  "tradingProfits";

    // 投机
    public static final String SPECULATE                   =  "speculate";

    // 资本保值
    public static final String CAPITALPRESERVATION         =  "capitalPreservation";

    // 知识水平为零
    public static final String KNOWLEDGELEVELISZERO        =  "knowledgeLevelIsZero";

    // 知识水平有限
    public static final String LIMITEDKNOWLEDGELEVEL       =  "limitedKnowledgeLevel";

    // 默认安全验证策略
    public static final Integer SECURITY_POLICY_DEFAULT         = 0;
    
    // 安全验证策略启用SMS
    public static final Integer SECURITY_POLICY_NEEDSMS         = 1;
    
    // 安全验证策略启用GA
    public static final Integer SECURITY_POLICY_NEEDGA          = 2;
    
    // 安全验证策略启用SMS或GA
    public static final Integer SECURITY_POLICY_NEEDGAORSMS     = 3;
    
    // 安全验证策略启用SMS和GA
    public static final Integer SECURITY_POLICY_NEEDGAANDSMS    = 4;
    
    // 默认交易验证策略
    public static final Integer TRADE_POLICY_DEFAULT            = 0;
    
    // 交易验证策略两小时验证一次
    public static final Integer TRADE_POLICY_TWOHOUR            = 1;
    
    // 交易验证策略每次都验证
    public static final Integer TRADE_POLICY_EVERYTIME          = 2;
    
    // 验证方式：短信
    public static final String  ACCOUNT_VALID_SMS               = "sms";
    
    // 验证方式：GA
    public static final String  ACCOUNT_VALID_GA                = "ga";
    
    public static final String  ACCOUNT_GRANT_YES               = "yes";
    
    public static final String  ACCOUNT_GRANT_NO                = "no";
    
    public static final String  FROZEN_REASON_BIND_PHONE        = "冻结原因:绑定手机";
    
    public static final String  FROZEN_REASON_BIND_EMAIL        = "冻结原因:绑定邮箱";
    
    public static final String  FROZEN_REASON_CHANGE_PASSWORD   = "冻结原因:修改登陆密码";
    
    public static final String  FROZEN_REASON_CHANGE_FUNDPWD    = "冻结原因:修改资金密码";
    
    public static final String  FROZEN_REASON_CHANGE_WALLET     = "冻结原因:修改钱包账户提现";
    
    public static final String  FROZEN_REASON_DO_BUY_FAIR       = "冻结原因:无委托买入";
    
    public static final String  FROZEN_REASON_DO_SELL_FAIR      = "冻结原因:无委托卖出";
    
    public static final String  FROZEN_REASON_ACCEPT_PUSH       = "冻结原因:PUSH成交";
    
    public static final String  FROZEN_REASON_DO_PUSH_SELL_FAIR = "冻结原因:集市交易发起委托卖出";
    
    public static final String  FROZEN_REASON_DO_PUSH_BUY_FAIR  = "冻结原因:集市交易发起委托买入";
    
    public static final String  FROZEN_REASON_DO_WITHDRAW       = "冻结原因:提币申请";
    
    public static final String  FROZEN_REASON_DO_PUSH           = "冻结原因:发送PUSH请求";
    
    public static final String  FROZEN_REASON_DO_SELL           = "冻结原因:提币申请";
}
