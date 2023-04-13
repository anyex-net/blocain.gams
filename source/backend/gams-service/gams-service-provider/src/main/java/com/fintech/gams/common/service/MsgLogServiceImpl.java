/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.consts.MessageConst;
import com.fintech.gams.common.entity.MsgLog;
import com.fintech.gams.common.entity.Template;
import com.fintech.gams.common.mapper.MsgLogMapper;
import com.fintech.gams.common.mapper.TemplateMapper;
import com.fintech.gams.common.model.SMSResult;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.*;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息发送日志 服务实现类
 * <p>File：MsgLogServiceImpl.java </p>
 * <p>Title: MsgLogServiceImpl </p>
 * <p>Description:MsgLogServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
@Component
public class MsgLogServiceImpl extends GenericServiceImpl<MsgLog> implements MsgLogService
{
    protected MsgLogMapper  msgLogMapper;
    
    @Autowired(required = false)
    private TemplateMapper  templateMapper;
    
    @Autowired
    private GlobalProperies globalProperies;
    
    @Autowired
    private SMSClientUtils  smsClientUtils;
    
    @Autowired
    private AmazonSESUtils  amazonSESUtils;
    
    @Autowired(required = false)
    private JavaMailSender  sender;
    
    @Value("${spring.mail.username}")
    private String          formMail;
    
    @Value("${com.gams.trade.front.url}")
    private String          url;
    
    @Value("${com.gams.trade.front.findPwd}")
    private String          findPwd;
    
    @Autowired(required = false)
    public MsgLogServiceImpl(MsgLogMapper msgLogMapper)
    {
        super(msgLogMapper);
        this.msgLogMapper = msgLogMapper;
    }
    
    @Override
    @Async
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void sendRegisterMail(String toEmail, String activationCode, String lang) throws BusinessException
    {
        if (StringUtils.isBlank(toEmail) || StringUtils.isBlank(lang)) throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_PERFIX).append(GlobalConst.SEPARATOR).append(toEmail).toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey)))
        { // 一分钟内只允许发送一次短信
            log.error("一分钟内只允许发送一次邮件！");
            throw new BusinessException(CommonEnums.ERROR_MSG_SEND_FAILED);
        }
        RedisUtils.putObject(expireKey, toEmail, CacheConst.ONE_MINUTE_CACHE_TIME);
        Template template = templateMapper.findByKeyAndLang(MessageConst.TEMPLATE_EMAIL_REGISTER, MessageConst.EMAIL, lang);
        if (null == template) throw new BusinessException(CommonEnums.ERROR_TEMPLATE_NOTEXISTS);
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_EMAIL_PERFIX).append(GlobalConst.SEPARATOR).append(toEmail).toString();
        RedisUtils.putObject(cacheKey, activationCode, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        String validUrl = new StringBuffer(url).append("/register/activate?code=").append(activationCode).toString();
        String content = String.format(template.getContent(), validUrl);
        MsgLog msgLog = new MsgLog(MessageConst.EMAIL, toEmail, content, false, System.currentTimeMillis());
        try
        {
            msgLog.setId(SerialnoUtils.buildPrimaryKey());
            if (GlobalConst.EMAIL_PROVIDER_AMAZON.equals(globalProperies.getEmailProvider()))
            {
                amazonSESUtils.sendMail(template.getTitle(), content, toEmail);
            }
            else
            {
                MimeMessage mimeMessage = sender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setTo(toEmail);
                mimeMessageHelper.setFrom(formMail);
                mimeMessageHelper.setText(content, true);
                mimeMessageHelper.setSubject(template.getTitle());
                sender.send(mimeMessage);
            }
            msgLog.setStatus(true);
            log.info("发送给" + toEmail + " 邮件已经发送。 subject：" + template.getTitle());
        }
        catch (Exception e)
        {
            log.error("发送给" + toEmail + " send mail error subject：" + template.getTitle());
            log.error(e.getLocalizedMessage());
        }
        finally
        {
            msgLogMapper.insert(msgLog);
        }
    }
    
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void sendForgetMail(String toEmail, String lang) throws BusinessException
    {
        if (StringUtils.isBlank(toEmail) || StringUtils.isBlank(lang)) throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_PERFIX).append(GlobalConst.SEPARATOR).append(toEmail).toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey)))
        { // 一分钟内只允许发送一次短信
            log.error("一分钟内只允许发送一次邮件！");
            throw new BusinessException(CommonEnums.ERROR_MSG_SEND_FAILED);
        }
        RedisUtils.putObject(expireKey, toEmail, CacheConst.ONE_MINUTE_CACHE_TIME);
        Template template = templateMapper.findByKeyAndLang(MessageConst.TEMPLATE_EMAIL_FORGETPASS, MessageConst.EMAIL, lang);
        if (null == template) throw new BusinessException(CommonEnums.ERROR_TEMPLATE_NOTEXISTS);
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_EMAIL_PERFIX).append(GlobalConst.SEPARATOR).append(toEmail).toString();
        String randomKey = SerialnoUtils.randomNum(8);
        log.info("邮件验证码:" + randomKey);
        RedisUtils.putObject(cacheKey, randomKey, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        String validUrl = new StringBuffer(url).append(findPwd).append(randomKey).append("/").append(toEmail).toString();
        String content = String.format(template.getContent(), validUrl);
        MsgLog msgLog = new MsgLog(MessageConst.EMAIL, toEmail, content, false, System.currentTimeMillis());
        try
        {
            msgLog.setId(SerialnoUtils.buildPrimaryKey());
            if (GlobalConst.EMAIL_PROVIDER_AMAZON.equals(globalProperies.getEmailProvider()))
            {
                amazonSESUtils.sendMail(template.getTitle(), content, toEmail);
            }
            else
            {
                MimeMessage mimeMessage = sender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setTo(toEmail);
                mimeMessageHelper.setFrom(formMail);
                mimeMessageHelper.setText(content, true);
                mimeMessageHelper.setSubject(template.getTitle());
                sender.send(mimeMessage);
            }
            msgLog.setStatus(true);
            log.info("发送给" + toEmail + " 邮件已经发送。 subject：" + template.getTitle());
        }
        catch (Exception e)
        {
            log.error("发送给" + toEmail + " send mail error subject：" + template.getTitle());
            log.error(e.getLocalizedMessage());
        }
        finally
        {
            msgLogMapper.insert(msgLog);
        }
    }
    
    @Override
    public void sendMailCode(String toEmail, String lang) throws BusinessException
    {
        if (StringUtils.isBlank(toEmail) || StringUtils.isBlank(lang)) throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_PERFIX).append(GlobalConst.SEPARATOR).append(toEmail).toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey)))
        { // 一分钟内只允许发送一次短信
            log.error("一分钟内只允许发送一次邮件！");
            throw new BusinessException(CommonEnums.ERROR_MSG_SEND_FAILED);
        }
        RedisUtils.putObject(expireKey, toEmail, CacheConst.ONE_MINUTE_CACHE_TIME);
        Template template = templateMapper.findByKeyAndLang(MessageConst.TEMPLATE_EMAIL_VALICODE, MessageConst.EMAIL, lang);
        if (null == template) throw new BusinessException(CommonEnums.ERROR_TEMPLATE_NOTEXISTS);
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_EMAIL_PERFIX).append(GlobalConst.SEPARATOR).append(toEmail).toString();
        String randomKey = SerialnoUtils.randomNum(8);
        log.info("邮件验证码:" + randomKey);
        RedisUtils.putObject(cacheKey, randomKey, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        String content = String.format(template.getContent(), randomKey);
        MsgLog msgLog = new MsgLog(MessageConst.EMAIL, toEmail, content, false, System.currentTimeMillis());
        try
        {
            msgLog.setId(SerialnoUtils.buildPrimaryKey());
            if (GlobalConst.EMAIL_PROVIDER_AMAZON.equals(globalProperies.getEmailProvider()))
            {
                amazonSESUtils.sendMail(template.getTitle(), content, toEmail);
            }
            else
            {
                MimeMessage mimeMessage = sender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setTo(toEmail);
                mimeMessageHelper.setFrom(formMail);
                mimeMessageHelper.setText(content, true);
                mimeMessageHelper.setSubject(template.getTitle());
                sender.send(mimeMessage);
            }
            msgLog.setStatus(true);
            log.info("发送给" + toEmail + " 邮件已经发送。 subject：" + template.getTitle());
        }
        catch (Exception e)
        {
            log.error("发送给" + toEmail + " send mail error subject：" + template.getTitle());
            log.error(e.getLocalizedMessage());
        }
        finally
        {
            msgLogMapper.insert(msgLog);
        }
    }
    
    @Override
    public void sendRemindEmail(String toEmail, String templateKey, String lang, Object ... args) throws BusinessException
    {
        if (StringUtils.isBlank(toEmail)) return;
        Template template = templateMapper.findByKeyAndLang(templateKey, MessageConst.EMAIL, lang);
        if (null == template) return;
        String content = String.format(template.getContent(), args);
        MsgLog msgLog = new MsgLog(MessageConst.EMAIL, toEmail, content, Boolean.FALSE, System.currentTimeMillis());
        msgLog.setId(SerialnoUtils.buildPrimaryKey());
        try
        {
            msgLog.setId(SerialnoUtils.buildPrimaryKey());
            if (GlobalConst.EMAIL_PROVIDER_AMAZON.equals(globalProperies.getEmailProvider()))
            {
                amazonSESUtils.sendMail(template.getTitle(), content, toEmail);
            }
            else
            {
                MimeMessage mimeMessage = sender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setTo(toEmail);
                mimeMessageHelper.setFrom(formMail);
                mimeMessageHelper.setText(content, true);
                mimeMessageHelper.setSubject(template.getTitle());
                sender.send(mimeMessage);
            }
            msgLog.setStatus(true);
            log.info("发送给" + toEmail + " 邮件已经发送。 subject：" + template.getTitle());
        }
        catch (Exception e)
        {
            log.error("发送给" + toEmail + " send mail error subject：" + template.getTitle());
            log.error(e.getLocalizedMessage());
        }
        finally
        {
            msgLogMapper.insert(msgLog);
        }
    }
    
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void sendChangeMail(String toEmail, String lang) throws BusinessException
    {
        if (StringUtils.isBlank(toEmail) || StringUtils.isBlank(lang)) throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_PERFIX).append(GlobalConst.SEPARATOR).append(toEmail).toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey)))
        { // 一分钟内只允许发送一次短信
            log.error("一分钟内只允许发送一次邮件！");
            throw new BusinessException(CommonEnums.ERROR_MSG_SEND_FAILED);
        }
        RedisUtils.putObject(expireKey, toEmail, CacheConst.ONE_MINUTE_CACHE_TIME);
        Template template = templateMapper.findByKeyAndLang(MessageConst.TEMPLATE_EMAIL_CHANGE, MessageConst.EMAIL, lang);
        if (null == template) throw new BusinessException(CommonEnums.ERROR_TEMPLATE_NOTEXISTS);
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_EMAIL_PERFIX).append(GlobalConst.SEPARATOR).append(toEmail).toString();
        String randomKey = SerialnoUtils.randomNum(8);
        log.info("邮件验证码:" + randomKey);
        RedisUtils.putObject(cacheKey, randomKey, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        String content = String.format(template.getContent(), randomKey);
        MsgLog msgLog = new MsgLog(MessageConst.EMAIL, toEmail, content, false, System.currentTimeMillis());
        try
        {
            msgLog.setId(SerialnoUtils.buildPrimaryKey());
            if (GlobalConst.EMAIL_PROVIDER_AMAZON.equals(globalProperies.getEmailProvider()))
            {
                amazonSESUtils.sendMail(template.getTitle(), content, toEmail);
            }
            else
            {
                MimeMessage mimeMessage = sender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setTo(toEmail);
                mimeMessageHelper.setFrom(formMail);
                mimeMessageHelper.setText(content, true);
                mimeMessageHelper.setSubject(template.getTitle());
                sender.send(mimeMessage);
            }
            msgLog.setStatus(true);
            log.info("发送给" + toEmail + " 邮件已经发送。 subject：" + template.getTitle());
        }
        catch (Exception e)
        {
            log.error("发送给" + toEmail + " send mail error subject：" + template.getTitle());
            log.error(e.getLocalizedMessage());
        }
        finally
        {
            msgLogMapper.insert(msgLog);
        }
    }
    
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void sendPhoneSMS(String region, String phone, String lang) throws BusinessException
    {
        if (StringUtils.isBlank(region) || StringUtils.isBlank(phone))
        { // 参数验证
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_PERFIX).append(GlobalConst.SEPARATOR).append(phone).toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey)))
        { // 一分钟内只允许发送一次短信
            log.error("一分钟内只允许发送一次短信！");
            throw new BusinessException(CommonEnums.ERROR_MSG_SEND_FAILED);
        }
        RedisUtils.putObject(expireKey, phone, CacheConst.ONE_MINUTE_CACHE_TIME);
        Template template = templateMapper.findByKeyAndLang(MessageConst.TEMPLATE_SMS_SENDVALICODE, MessageConst.SMS, lang);
        if (null == template)
        { throw new BusinessException(CommonEnums.ERROR_TEMPLATE_NOTEXISTS); }
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_SMS_PERFIX).append(GlobalConst.SEPARATOR).append(phone).toString();
        String randomKey = SerialnoUtils.randomNum(6);
        log.info("短信验证码:" + randomKey);
        RedisUtils.putObject(cacheKey, randomKey, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        String content = String.format(template.getContent(), randomKey);
        String mobile = new StringBuffer(region).append(phone).toString();
        MsgLog msgLog = new MsgLog(MessageConst.SMS, mobile, content, false, System.currentTimeMillis());
        try
        {
            msgLog.setId(SerialnoUtils.buildPrimaryKey());
            SMSResult result;
            if (GlobalConst.CHINA_PHONE_CODE.equals(region))
            {// 如果是国内短信就直接调用国内短信服务
                // result = smsClientUtils.sendSMS(phone, content);
                result = smsClientUtils.sendIntSMS(mobile, content);
            }
            else
            {
                result = smsClientUtils.sendIntSMS(mobile, content);
            }
            if (StringUtils.isNotBlank(result.getMsgid()))
            {// 表示发送成功
                msgLog.setStatus(true);
            }
            log.info("发送给" + mobile + " 的短信已经发送。subject:" + template.getTitle());
        }
        catch (Exception e)
        {
            log.error("发送给" + mobile + " 的短信出现问题。subject:" + template.getTitle());
            log.error(e.getLocalizedMessage());
        }
        finally
        {
            msgLogMapper.insert(msgLog);
        }
    }
    
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void sendForgetSMS(String region, String phone, String lang) throws BusinessException
    {
        if (StringUtils.isBlank(region) || StringUtils.isBlank(phone))
        { // 参数验证
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_PERFIX).append(GlobalConst.SEPARATOR).append(phone).toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey)))
        { // 一分钟内只允许发送一次短信
            log.error("一分钟内只允许发送一次短信！");
            throw new BusinessException(CommonEnums.ERROR_MSG_SEND_FAILED);
        }
        RedisUtils.putObject(expireKey, phone, CacheConst.ONE_MINUTE_CACHE_TIME);
        Template template = templateMapper.findByKeyAndLang(MessageConst.TEMPLATE_SMS_FORGETPASS, MessageConst.SMS, lang);
        if (null == template) throw new BusinessException(CommonEnums.ERROR_TEMPLATE_NOTEXISTS);
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_SMS_PERFIX).append(GlobalConst.SEPARATOR).append(phone).toString();
        String randomKey = SerialnoUtils.randomNum(6);
        log.info("短信验证码:" + randomKey);
        RedisUtils.putObject(cacheKey, randomKey, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        String content = String.format(template.getContent(), randomKey);
        String mobile = new StringBuffer(region).append(phone).toString();
        MsgLog msgLog = new MsgLog(MessageConst.SMS, mobile, content, false, System.currentTimeMillis());
        try
        {
            msgLog.setId(SerialnoUtils.buildPrimaryKey());
            SMSResult result;
            if (GlobalConst.CHINA_PHONE_CODE.equals(region))
            {// 如果是国内短信就直接调用国内短信服务
                // result = smsClientUtils.sendSMS(phone, content);
                result = smsClientUtils.sendIntSMS(mobile, content);
            }
            else
            {
                result = smsClientUtils.sendIntSMS(mobile, content);
            }
            if (StringUtils.isNotBlank(result.getMsgid()))
            {// 表示发送成功
                msgLog.setStatus(true);
            }
            log.info("发送给" + mobile + " 的短信已经发送。subject:" + template.getTitle());
        }
        catch (Exception e)
        {
            log.error("发送给" + mobile + " 的短信出现问题。subject:" + template.getTitle());
            log.error(e.getLocalizedMessage());
        }
        finally
        {
            msgLogMapper.insert(msgLog);
        }
    }
    
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void sendBindPhoneSMS(String region, String phone, String lang) throws BusinessException
    {
        sendPhoneSMS(region, phone, lang);
    }
    
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void sendChangePhoneSMS(String region, String phone, String lang) throws BusinessException
    {
        if (StringUtils.isBlank(region) || StringUtils.isBlank(phone))
        { // 参数验证
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_PERFIX).append(GlobalConst.SEPARATOR).append(phone).toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey)))
        { // 一分钟内只允许发送一次短信
            log.error("一分钟内只允许发送一次短信！");
            throw new BusinessException(CommonEnums.ERROR_MSG_SEND_FAILED);
        }
        RedisUtils.putObject(expireKey, phone, CacheConst.ONE_MINUTE_CACHE_TIME);
        Template template = templateMapper.findByKeyAndLang(MessageConst.TEMPLATE_SMS_CHANGEPHONE, MessageConst.SMS, lang);
        if (null == template)
        { throw new BusinessException(CommonEnums.ERROR_TEMPLATE_NOTEXISTS); }
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_SMS_PERFIX).append(GlobalConst.SEPARATOR).append(phone).toString();
        String randomKey = SerialnoUtils.randomNum(6);
        log.info("短信验证码:" + randomKey);
        RedisUtils.putObject(cacheKey, randomKey, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        String content = String.format(template.getContent(), randomKey);
        String mobile = new StringBuffer(region).append(phone).toString();
        MsgLog msgLog = new MsgLog(MessageConst.SMS, mobile, content, false, System.currentTimeMillis());
        try
        {
            msgLog.setId(SerialnoUtils.buildPrimaryKey());
            SMSResult result;
            if (GlobalConst.CHINA_PHONE_CODE.equals(region))
            {// 如果是国内短信就直接调用国内短信服务
                // result = smsClientUtils.sendSMS(phone, content);
                result = smsClientUtils.sendIntSMS(mobile, content);
            }
            else
            {
                result = smsClientUtils.sendIntSMS(mobile, content);
            }
            if (StringUtils.isNotBlank(result.getMsgid()))
            {// 表示发送成功
                msgLog.setStatus(true);
            }
            log.info("发送给" + mobile + " 的短信已经发送。subject:" + template.getTitle());
        }
        catch (Exception e)
        {
            log.error("发送给" + mobile + " 的短信出现问题。subject:" + template.getTitle());
            log.error(e.getLocalizedMessage());
        }
        finally
        {
            msgLogMapper.insert(msgLog);
        }
    }
    
    @Override
    public void cacheTempCode(String key, String value) throws BusinessException
    {
        RedisUtils.putObject(key, value, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
    }
    
    @Override
    public boolean validMailCode(String toEmail, String authCode) throws BusinessException
    {
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_EMAIL_PERFIX).append(GlobalConst.SEPARATOR).append(toEmail).toString();
        if (authCode.equals(RedisUtils.get(cacheKey)))
        { // 验证成功
            RedisUtils.del(cacheKey);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean validSMSCode(String phone, String authCode) throws BusinessException
    {
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_SMS_PERFIX).append(GlobalConst.SEPARATOR).append(phone).toString();
        if (authCode.equals(RedisUtils.get(cacheKey)))
        { // 验证成功
            RedisUtils.del(cacheKey);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean validTempCode(String key, String value) throws BusinessException
    {
        if (value.equals(RedisUtils.get(key)))
        { // 验证成功
            return true;
        }
        return false;
    }
}
