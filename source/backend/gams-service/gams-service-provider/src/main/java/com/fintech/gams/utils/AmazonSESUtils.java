package com.fintech.gams.utils;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fintech.gams.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

/**
 * AmazonSESUtils Introduce
 * <p>Title: AmazonSESUtils</p>
 * <p>File：AmazonSESUtils.java</p>
 * <p>Description: AmazonSESUtils</p>
 * <p>Copyright: Copyright (c) 2018/4/26</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class AmazonSESUtils
{
    @Value("${com.amazon.ses.host}")
    private String  HOST;
    
    @Value("${com.amazon.ses.port}")
    private Integer PORT = 587;
    
    @Value("${com.amazon.ses.fromuser}")
    private String  FROM_NAME;
    
    @Value("${com.amazon.ses.password}")
    private String  SMTP_PASSWORD;
    
    @Value("${com.amazon.ses.personal}")
    private String  FROM_PERSONAL;
    
    @Value("${com.amazon.ses.username}")
    private String  SMTP_USERNAME;

    private String CONFIGSET = "mof-mail";

    /**
     * 发送亚马逊邮件
     * @param subject 主题
     * @param body 内容
     * @param to 接收帐户
     * @param cc 抄送帐户
     * @throws BusinessException
     */
    public void sendMail(String subject, String body, String to, String ... cc) throws BusinessException
    {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        Transport transport = null;
        try
        {
            msg.setFrom(new InternetAddress(FROM_NAME, FROM_PERSONAL));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            if (null != cc)
            {// 是否添加抄送
                Address[] addresses = new InternetAddress[cc.length];
                for (int i = 0; i < cc.length; i++)
                {
                    addresses[i] = new InternetAddress(cc[i]);
                }
                msg.setRecipients(Message.RecipientType.CC, addresses);
            }
            msg.setSubject(subject);
            msg.setContent(body, "text/html;charset=utf-8");
            // msg.setHeader("X-SES-CONFIGURATION-SET", "ConfigSet");
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
            transport = session.getTransport();
            transport.connect(HOST, SMTP_USERNAME, EncryptUtils.desDecrypt(SMTP_PASSWORD));
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        }
        catch (Exception ex)
        {
            throw new BusinessException("The email was not sent." + ex.getLocalizedMessage());
        }
        finally
        {
            try
            {
                transport.close();
            }
            catch (MessagingException e)
            {
                log.error(e.getLocalizedMessage());
            }
        }
    }
    
    /**
     * 发送亚马逊邮件
     * @param subject 主题
     * @param body 内容
     * @param to 接收帐户
     * @param cc 抄送帐户
     * @throws BusinessException
     */
    public void sendMail(String subject, String body, String to, InternetAddress[] cc) throws BusinessException
    {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        Transport transport = null;
        try
        {
            msg.setFrom(new InternetAddress(FROM_NAME, FROM_PERSONAL));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            if (null != cc)
            {// 是否添加抄送
                msg.setRecipients(Message.RecipientType.CC, cc);
            }
            msg.setSubject(subject);
            msg.setContent(body, "text/html;charset=utf-8");
            // msg.setHeader("X-SES-CONFIGURATION-SET", "ConfigSet");
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
            transport = session.getTransport();
            transport.connect(HOST, SMTP_USERNAME, EncryptUtils.desDecrypt(SMTP_PASSWORD));
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        }
        catch (Exception ex)
        {
            throw new BusinessException("The email was not sent." + ex.getLocalizedMessage());
        }
        finally
        {
            try
            {
                transport.close();
            }
            catch (MessagingException e)
            {
                log.error(e.getLocalizedMessage());
            }
        }
    }
}
