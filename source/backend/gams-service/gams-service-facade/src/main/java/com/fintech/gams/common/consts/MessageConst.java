package com.fintech.gams.common.consts;

/**
 * 消息模版常量
 * <p>File：MessageConst.java</p>
 * <p>Title: MessageConst</p>
 * <p>Description:MessageConst</p>
 * <p>Copyright: Copyright (c) 2017/7/21</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public abstract class MessageConst
{
    /**
     *  邮件类型
     */
    public static final String EMAIL                                   = "email";
    
    /**
     * 短信类型
     */
    public static final String SMS                                     = "sms";
    
    /**
     * 邮件验证码(通用交易验证时使用)
     */
    public static final String TEMPLATE_EMAIL_GENERAL_SENDVALICODE     = "email_send_general_valid_code";
    
    /**
     *  发送激活提现地址邮件
     */
    public static final String TEMPLATE_SEND_EMAIL_ACTIVE_COLLECT_ADDR = "email_send_active_collect_addr";
    
    /**
     *  发送激活提现邮件
     */
    public static final String TEMPLATE_SEND_EMAIL_ACTIVE_RAISE        = "email_send_active_raise";
    
    /**
     * 重置密码
     */
    public static final String TEMPLATE_EMAIL_RESETPASSWORD            = "email_send_reset_pass";
    
    /**
     * 账户注册
     */
    public static final String TEMPLATE_EMAIL_REGISTER                 = "email_send_register";
    
    /**
     * 绑定邮件
     */
    public static final String TEMPLATE_EMAIL_BIND                     = "email_bind_confirm_code";
    
    /**
     * 发送手机验证码
     */
    public static final String TEMPLATE_SMS_SENDVALICODE               = "sms_send_valid_code";
    
    /**
     * 发送找回密码
     */
    public static final String TEMPLATE_SMS_FORGETPASS                 = "sms_send_forget_code";
    
    /**
     * 变更手机号
     */
    public static final String TEMPLATE_SMS_CHANGEPHONE                = "sms_send_change_code";
    
    /**
     * 找回密码
     */
    public static final String TEMPLATE_EMAIL_FORGETPASS               = "email_forget_pass_code";
    
    /**
     * 邮件验证码
     */
    public static final String TEMPLATE_EMAIL_VALICODE                 = "email_send_valid_code";
    
    /**
     * 变更邮箱
     */
    public static final String TEMPLATE_EMAIL_CHANGE                   = "email_send_change_code";
    
    /**
     * 提现邮件
     */
    public static final String TEMPLATE_EMAIL_WITHDRAWAL               = "email_withdrawal_confirm_code";

    /**
     * TRADE用户注册
     */
    public static final String TRADE_TEMPLATE_EMAIL_REGISTER           = "email_send_register_code";
    
    /**
     * 移动端注册
     */
    public static final String TEMPLATE_MOBILE_REGISTER                = "mobile_email_register";
    
    /**
     *  发送预警邮件
     */
    public static final String TEMPLATE_SEND_EMAIL_ALARM_GENR          = "email_send_alarm_genr";
    
    /**
     *  发送线程监控预警邮件
     */
    public static final String TEMPLATE_SEND_EMAIL_ALARM_THREAD        = "email_send_alarm_thread";
    
    /**
     *  发送预警短信
     */
    public static final String TEMPLATE_SEND_SMS_ALARM_GENR            = "sms_send_alarm_genr";
    
    /**
     *  发送外部指数异常邮件
     */
    public static final String TEMPLATE_SEND_EMAIL_QUOTATATION_IDX     = "email_send_quotation_idx_error";
    
    /**
     *  发送外部指数异常短信
     */
    public static final String TEMPLATE_SEND_SMS_QUOTATATION_IDX       = "sms_send_quotation_idx_error";
    
    /**
     * 登陆提醒
     */
    public static final String REMIND_LOGIN_EMAIL                      = "email_remind_login";
    
    public static final String REMIND_LOGIN_PHONE                      = "phone_remind_login";
    
    /**
     * 修改登陆密码提醒
     */
    public static final String REMIND_CHANGE_LOGINPASS_EMAIL           = "remind_change_loginpwd_email";
    
    public static final String REMIND_CHANGE_LOGINPASS_PHONE           = "remind_change_loginpwd_phone";
    
    /**
     * 修改资金密码提醒
     */
    public static final String REMIND_CHANGE_FUNDPASS_EMAIL            = "remind_change_fundpwd_email";
    
    public static final String REMIND_CHANGE_FUNDPASS_PHONE            = "remind_change_fundpwd_phone";
    
    /**
     * 变更手机提醒
     */
    public static final String REMIND_CHANGE_PHONE_EMAIL               = "remind_change_phone_email";
    
    public static final String REMIND_CHANGE_PHONE_PHONE               = "remind_change_phone_phone";
    
    /**
     * 修改GA提醒
     */
    public static final String REMIND_CHANGE_GOOGLE_EMAIL              = "remind_change_google_email";
    
    public static final String REMIND_CHANGE_GOOGLE_PHONE              = "remind_change_google_phone";
    
    /**
     * 强制平仓提醒
     */
    public static final String REMIND_CLOSE_POSITION_EMAIL             = "remind_close_position_email";
    
    public static final String REMIND_CLOSE_POSITION_PHONE             = "remind_close_position_phone";
    
    /**
     * 提币二次确认短信提醒
     */
    public static final String REMIND_WITHDRAWCONFIRM_PHONE            = "remind_withdrawConfirm_phone";
    
    /**
     * 新增提币地址短信提醒
     */
    public static final String REMIND_ADDWITHDRAWADDR_PHONE            = "remind_addWithdrawAddr_phone";
    
    /**
     * 钱包与合约账户互转提醒
     */
    public static final String REMIND_CONVIERSION_PHONE                = "remind_conversion_phone";
    
    /**
     * 钱包与合约账户互转提醒
     */
    public static final String REMIND_CONVIERSION_EMAIL                = "remind_conversion_email";
    
    /**
     * 账户冻结短信提醒
     */
    public static final String REMIND_FROZEN_PHONE                     = "remind_frozen_phone";
    
    /**
     * 账户冻结邮箱提醒
     */
    public static final String REMIND_FROZEN_EMAIL                     = "remind_frozen_email";
    
    /**
     * 账户解冻短信提醒
     */
    public static final String REMIND_THAW_PHONE                       = "remind_thaw_phone";
    
    /**
     * 账户解冻邮箱提醒
     */
    public static final String REMIND_THAW_EMAIL                       = "remind_thaw_email";
    
    /**
     * 账户设备管理
     */
    public static final String MOBILE_EMAIL_TRUST_DEVICE               = "mobile_email_trust_device";
}
