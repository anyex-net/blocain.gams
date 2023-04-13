package com.fintech.gams.enums;

import com.fintech.gams.bean.EnumDescribable;

/**
 * <p>File：CommonEnums.java </p>
 * <p>Title: CommonEnums </p>
 * <p>Description: CommonEnums </p>
 * <p>Copyright: Copyright (c) 15/9/15</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public enum CommonEnums implements EnumDescribable
{
    SUCCESS(200, "success"), // 操作成功
    FAIL(400, "failed"), // 操作失败
    UNAUTHORIZED(401, "unauthorized"), // 未认证（签名错误）
    LOGIN_FAIL(402, "username or password verification failure"), // 接口不存在
    NOT_FOUND(404, "not found"), // 接口不存在
    DB_UNIQUE_ERROR(500, "Database duplicate data exception"), // 数据库约束异常、重复数据异常
    INTERNAL_SERVER_ERROR(500, "Server internal error"), // 服务器内部错误
    SERVER_BUSY_ERROR(501, "Server internal error,Please try later"), // 服务器内部错误
    ERROR_DATA_VALID_ERR(1001, "Data check failed"), // 数据校验失败
    NEED_POLICY_CHECK(2001, "Security policy"), // 安全验证策略
    PARAMS_VALID_ERR(2002, "Parameter validation error"), // 参数验证错误
    USER_NOT_LOGIN(2003, "Account not logged in"), // 用户未登陆
    ERROR_LOGIN_ACCOUNT(2004, "Account not exist"), // 用户不存在
    ERROR_FROZEN_ACCOUNT(2004, "Login Exception,Please contact us."), // 用户被冻结
    ERROR_LOGIN_PASSWORD(2005, "Wrong password"), // 密码错误
    ERROR_LOGIN_ACCOUNTPASSWORD(2004, "ERROR Incorrect username or password"), // 用户名或密码错误
    ERROR_LOGIN_TIMEOUT(2006, "Session timeout"), // 会话超时
    ERROR_LOGIN_CAPTCHA(2007, "Verification code error"), // 验证码错误
    ERROR_LOGIN_LOCK(2008, "The account has been locked"), // 帐户已锁
    ERROR_CSRF_VALID(2009, "CSRF Verification"), // CSRF验证
    ERROR_EMAIL_EXIST(2010, "Email has already exist"), // 邮箱已存在
    ERROR_ACCOUNT_EXIST(2010, "Account has already exist"), // 账户已存在
    ERROR_MOBILE_EXIST(2010, "Mobile number has already exist"), // 手机号码已存在
    ERROR_ILLEGAL_REQUEST(2011, "Illegal request"), // 非法请求
    ERROR_DES_CHECK_FAILED(2012, "DES Check failed"), // DES校验失败
    ERROR_DATA_LENGTH_FAILED(2013, "Data length Check failed"), // 数据长度校验失败
    ERROR_AUTHER_FAILED(2014, "Identity Authentication failed"), // 身份认证失败
    ERROR_DB_ACCESS_FAILED(2015, "System is busy,Data access failed,Please try later"), // 数据访问失败
    ERROR_DB_OPER_NOT_EXPECTED(2015, "Database oper not expected,Please check it"), // 数据库操作未到预期
    ERROR_EMAIL_VALID_FAILED(2016, "Email verification code error"), // 邮箱验证码失败
    ERROR_EMAIL_FORMAT_FAILED(2017, "Email format error"), // 邮箱格式错误
    ERROR_AFS_VALID_FAILED(2018, "AFS verification error"), // AFS失败
    ERROR_SMS_VALID_FAILED(2035, "SMS verification error"), // 短信验证失败
    ERROR_EMAIL_SEND_FAILED(2036, "SMS verification error"), // 邮件发送失败
    ERROR_MSG_SEND_FAILED(2037, "Only one short message is allowed in a minute"), // 一分钟内只允许发送一次短信
    ERROR_GA_VALID_FAILED(2018, "Google auth error"), // 谷歌验证失败
    ERROR_WALLET_VALID_FAILED(2019, "Payment password verification error"), // 资金密码验证失败
    ERROR_WALLET_VALID_NOEXIST(2020, "Payment password not set"), // 未设置资金密码
    ERROR_SESSION_TIME_OUT(2021, "Session timeout"), // 会话已过期
    ERROR_GT_ONCE_MAX_AMT(2022, "More than a single maximum amount"), // 已超过单笔最大限额
    ERROR_GT_DAY_MAX_AMT(2022, "Day maximum amount limit was exceeded"), // 已超过当天最大限额
    ERROR_GT_DAY_MAX_CNT(2028, "Day maximum count limit was exceeded"), // 已超过当天最大次数
    ERROR_GT_MAX_AMT(2023, "The maximum amount limit has been exceeded"), // 已超过最大限额
    ERROR_GT_MIN_AMT(2124, "The minimum amount limit has been exceeded"), // 已超过最小限额
    ERROR_GA_NOT_BIND(2024, "Google auth is unbound"), // GA未绑定
    ERROR_INVALID_ADDRESS(2025, "Withdrawal address is invalid"), // 无效的提币地址
    ERROR_RAISE_ADDR_EXIST(2026, "Withdrawal address already exist"), // 提币地址已经存在
    ERROR_ADDR_NOT_BIND(2027, "Withdrawal address is unbound"), // 提币地址未绑定
    ERROR_CAN_NOT_EXCHANGE_WITH_YOURSELF(2027, "You can't trade with yourself"), // 不能跟自己进行交易
    ERROR_PUBLIC_ENTRUST_GET_BTC_RANGE(2030, "Ask for BTC amount range"), // 索要BTC数量范围
    ERROR_PUBLIC_ENTRUST_BUY_PRICE_RANGE(2029, "Bid price [{0},{1}]"), // 委托买价格范围(大小限价)
    ERROR_PUBLIC_ENTRUST_SELL_PRICE_RANGE(2030, "Ask price [{0},{1}]"), // 委托卖价格范围(大小限价)
    ERROR_FUNCTION_OPEN(2031, "Suspended"), // 该功能尚未开放！
    USER_PERMISSION_NOTAVAILABLE(2032, "User insufficient permissions"), // 用户越权
    RISK_ENABLE_ENABLE_NOTAVAILABLE(3000, "Insufficient balance"), // 可用余额不足
    RISK_ENABLE_DEBIT_NOTAVAILABLE(3001, "Insufficient loan amount"), // 放贷余额不足
    RISK_TRADE_ENTRUST_NOTAVAILABLE(4001, "Order record error"), // 委托记录异常
    RISK_TRADE_ENTRUST_ALLDEAL_NOTWITHDRAW(4002, "All deals are done,Can not be canceled"), // 已全部成交不能撤单
    RISK_TRADE_ENTRUST_ENTRUSTSTATUS_NOTWITHDRAW(4003, "This order status can not cancel"), // 该委托状态不能撤单
    RISK_TRADE_ENTRUST_RIVAL_NOTEXITS(4004, "Order pairs do not exist"), // 委托交易对手不存在
    RISK_TRADE_TRADE_BIGGER_THEN_UNDEAL_AMOUNT(4005, "Deal amount is exceed"), // 成交数量大于委托未成交数量
    RISK_FUND_AMOUNT_SMALL_ZERO(5002, "The balance is negative"), // 资金金额小于零
    ERROR_MATCHTRADE_REACHED_EXPLOSTION_PRICE(5003, "The price has been reached Liquidation"), // 已达到爆仓价
    ERROR_GT_MAX_ORDER_CNT(5004, "The maximum order count is exceeded"), // 已超过最大下单量
    ERROR_DEBIT_GT_ZREO(5005, "Transfer limited[Debt exist]"), // 存在负债，无法转出。
    ERROR_NOT_SUPPORT_RECHARGE(5006, "The deposit of this token is not supported recently."), // 该币种暂不支持充值
    ERROR_NOT_SUPPORT_WITHDRAW(5006, "The withdrawal of this token is not supported recently."), // 该币种暂不支持提取
    ERROR_REQUEST_EXPIRED(5007, "The request has expired, place check your device time."), // request请求已过期, 请校验你的设备时间
    ERROR_DEVICE_TYPE(5101, "Unknown terminal type"), // 未知的终端类型
    ERROR_DEVICE_TYPE_UNSUPPORT(5102, "Unsupported terminal type"), // 不支持的终端类型
    ERROR_VERSION_TOLOW(5103, "The current version is too low and needs to be upgraded!"), // 版本太低需要升级
    ERROR_RSA_EXIST(5104, "RSA HAS EXIST!"), // 密钥已存在
    ERROR_ENABLE_TOKEN(5105, "Please enable the token trading first!"), // 未开启交易
    ERROR_IPADDR_EXIST(5106, "IP address already exists!"), // ip地址已存在
    ERROR_INVALID_APIKEY(5107, "API_KEY is invalid"), // 无效的Api_key
    ERROR_APIKEY_MAX(5109, "Each user can create 3 api_key maximum."), // 超过用户创建API最大数量
    ERROR_KEYTYPE_MAX(5109, "User can only create one type of API"), // 每个用户最多创建一种类型的API
    ERROR_REQUEST_IPADDR(5110, "Incorrect IP address"), // API访问IP地址不正确
    ERROR_API_EXPIRED(5111, "The API has expired"), // API证书已过期
    ERROR_API_TYPE(5112, "API keyType is invalid"), // 无效的证书类型
    ERROR_API_MUST_IPGROUP(5113, "You must apply the IP Access Restriction filter in order to enable withdrawals"), // API提币权限必须绑定IP
    ERROR_API_PERMISSION(5114, "Insufficient API certificate permissions"), // API证书权限不足
    ERROR_API_MAX_IPGROUP(5115, "The maximum IP address limit has been exceeded"), // 超过API的IP地址最大绑定数量
    ERROR_API_IPGROUP_VALID_ERR(5116, "IP Address check failed"), // IP地址校验失败
    ERROR_VERSION_TOUPDATE(5108, "The current version is too low and must upgrade!"), // 版本不支持，强制升级
    ERROR_CLOSE_POSITION(5109, "You have no position !"), // 无仓可平
    ERROR_CLOSE_POSITION_LIMIT(5110, "Position limit"), // 平仓限制
    ERROR_NEED_RISKEVALUATION(5117, "Please complete the risk evaluation first"), // 风险测评
    ERROR_LOCK_WITHDRAW(6001, "For the security of your funds,withdrawals are not permitted within 24 hours after changing fund password / mobile number / Google Authenticator settings."), // 您在24小时内有安全操作不能进行提现
    ERROR_SYS_BUSY(9001, "The system is busy,please try again later"), //
    ERROR_PAYMENT_REPEAT(6002, "The deposit cannot be resubmitted"), //
    ERROR_ACCOUNT_NOT_REGISTER(6003, "Account have not registered"),// 抢红包活动 , 账户未注册
    ERROR_TEMPLATE_NOTEXISTS(7001, "The message template does not exist"),// 消息模版不存在
    ERROR_AWS_FILE_TRANSFER(8001, "Aws S3 file transfer failed"),// Aws S3文件转移失败
    ;
    public Integer code;
    
    public String  message;
    
    CommonEnums(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    /**
     * 根据状态码获取状态码描述
     *
     * @param code 状态码
     * @return String 状态码描述
     */
    public static String getMessage(Integer code)
    {
        String result = null;
        for (CommonEnums c : CommonEnums.values())
        {
            if (c.code.equals(code))
            {
                result = c.message;
                break;
            }
        }
        return result;
    }
    
    @Override
    public Integer getCode()
    {
        return this.code;
    }
    
    public void setCode(Integer code)
    {
        this.code = code;
    }
    
    @Override
    public String getMessage()
    {
        return this.message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
}
