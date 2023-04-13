package com.fintech.gams.utils;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.fintech.gams.common.model.SMSModel;
import com.fintech.gams.common.model.SMSResult;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;

/**
 * 短信发送客户端 Introduce
 * <p>File：SMSClient.java</p>
 * <p>Title: SMSClient</p>
 * <p>Description: SMSClient</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class SMSClientUtils
{
    @Value("${com.gams.sms.signature.en}")
    private String SEND_SIGNATURE_EN;
    
    @Value("${com.gams.sms.signature.cn}")
    private String SEND_SIGNATURE_CN;
    
    @Value("${com.gams.sms.host}")
    private String SEND_HOST;
    
    @Value("${com.gams.sms.user}")
    private String SEND_USER;
    
    @Value("${com.gams.sms.pass}")
    private String SEND_PASS;
    
    @Value("${com.gams.sms.inthost}")
    private String SEND_INTHOST;
    
    @Value("${com.gams.sms.intuser}")
    private String SEND_INTUSER;
    
    @Value("${com.gams.sms.intpass}")
    private String SEND_INTPASS;
    
    /**
     * 发送国内短信服务
     * @param mobile
     * @param content
     * @return {@link SMSResult}
     * @throws BusinessException
     */
    public SMSResult sendSMS(String mobile, String content) throws BusinessException
    {
        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(content))
        {// 防止空指针
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        SMSModel model = new SMSModel();
        model.setAccount(SEND_USER);
        model.setPassword(EncryptUtils.desDecrypt(SEND_PASS));
        model.setPhone(mobile);
        model.setMsg(content);
        model.setReport(true);
        if (StringUtils.isNotBlank(SEND_SIGNATURE_CN))
        {
            StringBuffer sms = new StringBuffer("【").append(SEND_SIGNATURE_CN).append("】").append(content);
            model.setMsg(sms.toString());
        }
        String params = JSON.toJSONString(model);
        CloseableHttpClient client = HttpUtils.getHttpClient2();
        String response = HttpUtils.postWithJSON(client, SEND_HOST, params);
        HttpUtils.releaseHttpClient(client);
        if (StringUtils.isBlank(response)) return null;
        return JSON.parseObject(response, SMSResult.class);
    }
    
    /**
     * 发送国际短信服务
     * @param mobile
     * @param content
     * @return {@link SMSResult}
     * @throws BusinessException
     */
    public SMSResult sendIntSMS(String mobile, String content) throws BusinessException
    {
        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(content))
        {// 防止空指针
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        SMSModel model = new SMSModel();
        model.setAccount(SEND_INTUSER);
        model.setPassword(EncryptUtils.desDecrypt(SEND_INTPASS));
        model.setMobile(mobile);
        if (StringUtils.isNotBlank(SEND_SIGNATURE_EN))
        {
            StringBuffer sms = new StringBuffer("[").append(SEND_SIGNATURE_EN).append("]").append(content);
            model.setMsg(sms.toString());
        }
        String params = JSON.toJSONString(model);
        CloseableHttpClient client = HttpUtils.getHttpClient2();
        String response = HttpUtils.postWithJSON(client, SEND_INTHOST, params);
        HttpUtils.releaseHttpClient(client);
        if (StringUtils.isBlank(response)) return null;
        return JSON.parseObject(response, SMSResult.class);
    }
    
    public void main(String[] args) throws BusinessException
    {
        SMSResult result = sendIntSMS("8615306620878", "BITMS SMS TEST");
        System.out.println(JSON.toJSONString(result));
    }
}
