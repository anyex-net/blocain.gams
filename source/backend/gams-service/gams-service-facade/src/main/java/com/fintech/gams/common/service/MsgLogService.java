/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.common.entity.MsgLog;
import com.fintech.gams.exception.BusinessException;

/**
 * 消息发送日志 服务接口
 * <p>File：MsgLogService.java </p>
 * <p>Title: MsgLogService </p>
 * <p>Description:MsgLogService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface MsgLogService extends GenericService<MsgLog>
{
    /**
     * 发送注册账户邮件
     * @param toEmail
     * @param activationCode
     * @param lang
     * @throws BusinessException
     */
    void sendRegisterMail(String toEmail, String activationCode, String lang) throws BusinessException;
    
    /**
     * 发送找回密码邮件
     * @param toEmail
     * @param lang
     * @throws BusinessException
     */
    void sendForgetMail(String toEmail, String lang) throws BusinessException;
    
    /**
     * 向当前邮箱地址发验证码
     * @param toEmail
     * @param lang
     * @throws BusinessException
     */
    void sendMailCode(String toEmail, String lang) throws BusinessException;
    
    /**
     * 发送更换邮箱地址邮件
     * @param toEmail
     * @param lang
     * @throws BusinessException
     */
    void sendChangeMail(String toEmail, String lang) throws BusinessException;
    
    /**
     * 发送邮件提醒
     *
     * @param email
     * @param templateKey
     * @param lang
     * @param args
     * @throws BusinessException
     */
    void sendRemindEmail(String email, String templateKey, String lang, Object ... args) throws BusinessException;
    
    /**
     * 发送启用手机登陆短信
     * @param region
     * @param phone
     * @param lang
     * @throws BusinessException
     */
    void sendPhoneSMS(String region, String phone, String lang) throws BusinessException;
    
    /**
     * 发送找回密码短信
     * @param region
     * @param phone
     * @param lang
     * @throws BusinessException
     */
    void sendForgetSMS(String region, String phone, String lang) throws BusinessException;
    
    /**
     * 发送已绑定手机短信验证码
     * @param region
     * @param phone
     * @param lang
     * @throws BusinessException
     */
    void sendBindPhoneSMS(String region, String phone, String lang) throws BusinessException;
    
    /**
     * 发送更换手机短信
     * @param region
     * @param phone
     * @param lang
     * @throws BusinessException
     */
    void sendChangePhoneSMS(String region, String phone, String lang) throws BusinessException;
    
    /**
     * 缓存临时的会话对象
     * @param key
     * @param value
     * @throws BusinessException
     */
    void cacheTempCode(String key, String value) throws BusinessException;
    
    /**
     * 邮件授权验证
     * @param toEmail
     * @param authCode
     * @return
     * @throws BusinessException
     */
    boolean validMailCode(String toEmail, String authCode) throws BusinessException;
    
    /**
     * 短信授权验证
     * @param phone
     * @param authCode
     * @return
     * @throws BusinessException
     */
    boolean validSMSCode(String phone, String authCode) throws BusinessException;
    
    /**
     * 验证临时的会话对象
     * @param key
     * @param value
     * @return
     * @throws BusinessException
     */
    boolean validTempCode(String key, String value) throws BusinessException;
}
