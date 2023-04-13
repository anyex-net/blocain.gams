package com.fintech.gams.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.account.consts.AccountConsts;
import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.model.PolicyModel;
import com.fintech.gams.common.service.MsgLogService;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.google.Authenticator;
import com.fintech.gams.utils.EncryptUtils;
import com.fintech.gams.utils.StringUtils;

/**
 * AccountPolicyServiceImpl
 * <p>File: AccountPolicyServiceImpl.java </p>
 * <p>Title: AccountPolicyServiceImpl </p>
 * <p>Description: AccountPolicyServiceImpl </p>
 * <p>Copyright: Copyright (c) 2019-07-02</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPolicyServiceImpl implements AccountPolicyService
{
    @Autowired
    private MsgLogService msgLogService;
    
    @Override
    public boolean validPassword(String plainPassword, String password)
    {
        boolean flag = false;
        if (StringUtils.isBlank(plainPassword) || StringUtils.isBlank(password)) return flag;
        if (EncryptUtils.validatePassword(plainPassword, password)) flag = true;
        return flag;
    }
    
    @Override
    public boolean validGaCode(String authKey, String validCode)
    {
        boolean flag = false;
        if (StringUtils.isBlank(authKey) || StringUtils.isBlank(validCode)) return flag;
        Authenticator authenticator = new Authenticator();
        if (authenticator.checkCode(EncryptUtils.desDecrypt(authKey), Long.valueOf(validCode))) flag = true;
        return flag;
    }
    
    @Override
    public boolean validSMSCode(String phone, String validCode)
    {
        return msgLogService.validSMSCode(phone, validCode);
    }
    
    @Override
    public boolean validEmailCode(String email, String validCode)
    {
        return msgLogService.validMailCode(email, validCode);
    }
    
    @Override
    public void validSecurityPolicy(Account account, PolicyModel policy) throws BusinessException
    {
        if (null == policy) throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        if (AccountConsts.SECURITY_POLICY_DEFAULT.equals(account.getSecurityPolicy()))
        {// 验证登陆密码
            if (!validPassword(policy.getPwd(), account.getLoginPwd()))
            { throw new BusinessException(CommonEnums.ERROR_LOGIN_PASSWORD); }
        }
        if (AccountConsts.SECURITY_POLICY_NEEDSMS.equals(account.getSecurityPolicy()))
        {// 短信验证
            if (!validSMSCode(account.getMobile(), policy.getSms()))
            { throw new BusinessException(CommonEnums.ERROR_SMS_VALID_FAILED); }
        }
    }
}
