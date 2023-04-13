package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.model.PolicyModel;
import com.fintech.gams.exception.BusinessException;

/**
 * 帐户策略服务
 * <p>File: AccountPolicyService.java </p>
 * <p>Title: AccountPolicyService </p>
 * <p>Description: AccountPolicyService </p>
 * <p>Copyright: Copyright (c) 2019-07-02</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountPolicyService
{
    /**
     * 验证帐户密码或资金密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return {@link Boolean}
     */
    boolean validPassword(String plainPassword, String password);
    
    /**
     * 验证GA码
     * @param authKey 私钥
     * @param validCode 难证码
     * @return {@link Boolean}
     */
    boolean validGaCode(String authKey, String validCode);
    
    /**
     * 验证手机验证码
     * @param phone 手机号码
     * @param validCode 验证码
     * @return {@link Boolean}
     */
    boolean validSMSCode(String phone, String validCode);
    
    /**
     * 校验邮件验证码
     * @param email 邮件
     * @param validCode 验证码
     * @return {@link Boolean}
     */
    boolean validEmailCode(String email, String validCode);
    
    /**
     * 验证安全策略，不包含密码验证
     * @param account 帐户信息
     * @param policy 策略
     * @throws BusinessException
     */
    void validSecurityPolicy(Account account, PolicyModel policy) throws BusinessException;
}
