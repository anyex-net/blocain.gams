package com.fintech.gams.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.model.ResetModel;
import com.fintech.gams.account.model.VerifyModel;
import com.fintech.gams.account.service.AccountService;
import com.fintech.gams.bean.AliyunAFS;
import com.fintech.gams.bean.AliyunModel;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.common.service.MsgLogService;
import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.EncryptUtils;
import com.fintech.gams.utils.RedisUtils;
import com.fintech.gams.utils.SerialnoUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 忘记密码控制器
 * <p>File: ForgetController.java </p>
 * <p>Title: ForgetController </p>
 * <p>Description: ForgetController </p>
 * <p>Copyright: Copyright (c) 2019-05-29</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@Api(description = "忘记密码控制器")
public class ForgetController extends GenericController
{
    @Autowired(required = false)
    private AliyunAFS      aliyunAFS;
    
    @Autowired(required = false)
    private AccountService accountService;
    
    @Autowired(required = false)
    private MsgLogService  msgLogService;
    
    @RequestMapping("/forgetPass/verify")
    @ApiOperation(value = "验证身份", httpMethod = "POST")
    public JsonMessage verifyIdentity(@ModelAttribute VerifyModel verify) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, verify))
        {// 先验证参数
            boolean flag;
            Account account = null;
            if (verify.isAuthType())
            {// 邮件找回
                flag = msgLogService.validMailCode(verify.getObject(), verify.getAuthCode());
                account = accountService.findByEmail(verify.getObject());
                if (null == account)
                { // 判断数据是否存在
                    throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT);
                }
            }
            else
            {// 短信找回
                flag = msgLogService.validSMSCode(verify.getObject(), verify.getAuthCode());
                account = accountService.findByPhone(null, verify.getObject());
                if (null == account)
                { // 判断数据是否存在
                    throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT);
                }
            }
            if (flag)
            {
                String randomKey = SerialnoUtils.buildUUID();
                String cacheKey = new StringBuffer(CacheConst.RESET_SECURITY_PREFIX).append(GlobalConst.SEPARATOR).append(randomKey).toString();
                RedisUtils.putObject(cacheKey, account, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
                json.setObject(randomKey);
            }
            else
            {
                json = getJsonMessage(CommonEnums.ERROR_LOGIN_CAPTCHA);
            }
        }
        return json;
    }
    
    @RequestMapping("/forgetPass/resetPwd")
    @ApiOperation(value = "重置密码", httpMethod = "POST")
    public JsonMessage resetPwd(@ModelAttribute ResetModel reset, @ModelAttribute AliyunModel model) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reset))
        {// 先验证参数
            if (!aliyunAFS.validParams(model))
            {// 验证滑块
                return this.getJsonMessage(CommonEnums.ERROR_LOGIN_CAPTCHA);
            }
            String cacheKey = new StringBuffer(CacheConst.RESET_SECURITY_PREFIX).append(GlobalConst.SEPARATOR).append(reset.getAuthCode()).toString();
            Account account = (Account) RedisUtils.getObject(cacheKey);
            if (null == account)
            { throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST); }
            account.setLoginPwd(EncryptUtils.entryptPassword(reset.getNewPwd()));
            accountService.updateByPrimaryKey(account);
        }
        return json;
    }
}
