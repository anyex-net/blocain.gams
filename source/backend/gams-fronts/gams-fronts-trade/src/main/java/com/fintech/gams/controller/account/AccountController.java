package com.fintech.gams.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.account.consts.AccountConsts;
import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.model.AccountInfoModel;
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
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * AccountController
 * <p>File: AccountController.java </p>
 * <p>Title: AccountController </p>
 * <p>Description: AccountController </p>
 * <p>Copyright: Copyright (c) 2019-05-21</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.ACCOUNT)
@Api(description = "交易帐户管理")
public class AccountController extends GenericController
{
    @Autowired(required = false)
    private AliyunAFS      aliyunAFS;
    
    @Autowired(required = false)
    private AccountService accountService;
    
    @Autowired(required = false)
    private MsgLogService  msgLogService;
    
    @PostMapping("/info")
    @ApiOperation(value = "取帐户信息", httpMethod = "POST")
    public JsonMessage getInfo() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        return getJsonMessage(CommonEnums.SUCCESS, account);
    }
    
    @PostMapping("/currentUserInfo")
    @ApiOperation(value = "取当前用户帐户信息", httpMethod = "POST")
    public JsonMessage getCurrentUserInfo() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        AccountInfoModel accountInfoModel = accountService.findAccountInfo(account.getId(), account.getAccountType());
        return getJsonMessage(CommonEnums.SUCCESS, accountInfoModel);
    }
    
    @PostMapping("/sendPhoneSMS")
    @ApiOperation(value = "发送启用手机登陆短信", httpMethod = "POST")
    public JsonMessage sendPhoneSMS(AliyunModel model) throws BusinessException
    {
        if (!aliyunAFS.validParams(model))
        {// 验证滑块
            throw new BusinessException(CommonEnums.ERROR_AFS_VALID_FAILED);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        msgLogService.sendPhoneSMS(account.getRegion(), account.getMobile(), GlobalConst.DEFAULT_LANG);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @ApiOperation(value = "启用开户手机登陆", httpMethod = "POST")
    @RequestMapping(value = "/enablePhone", method = RequestMethod.POST)
    public JsonMessage enablePhone(String valiCode) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (!msgLogService.validSMSCode(account.getMobile(), valiCode))
        {// 验证验证码
            throw new BusinessException(CommonEnums.ERROR_SMS_VALID_FAILED);
        }
        account.setSecurityPolicy(AccountConsts.SECURITY_POLICY_NEEDSMS);
        accountService.updateByPrimaryKey(account);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @PostMapping("/sendBindPhoneSMS")
    @ApiOperation(value = "向当前已绑定手机发送验证码", httpMethod = "POST")
    public JsonMessage sendBindPhoneSMS(AliyunModel model) throws BusinessException
    {
        if (!aliyunAFS.validParams(model))
        {// 验证滑块
            throw new BusinessException(CommonEnums.ERROR_AFS_VALID_FAILED);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        msgLogService.sendBindPhoneSMS(account.getRegion(), account.getMobile(), GlobalConst.DEFAULT_LANG);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @PostMapping("/checkBindPhoneSMS")
    @ApiOperation(value = "检测短信验证码", httpMethod = "POST")
    public JsonMessage checkBindPhoneSMS(String valiCode) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (!msgLogService.validSMSCode(account.getMobile(), valiCode))
        {// 验证邮箱
            throw new BusinessException(CommonEnums.ERROR_SMS_VALID_FAILED);
        }
        StringBuffer cacheKey = new StringBuffer(CacheConst.CACHE_CODE_PERFIX);
        cacheKey.append(GlobalConst.SEPARATOR).append(principal.getId());
        msgLogService.cacheTempCode(cacheKey.toString(), String.valueOf(principal.getId()));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @PostMapping("/sendChangePhoneSMS")
    @ApiOperation(value = "向新手机发送验证码", httpMethod = "POST")
    public JsonMessage sendChangePhoneSMS(String region, String phone, AliyunModel model) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        StringBuffer cacheKey = new StringBuffer(CacheConst.CACHE_CODE_PERFIX);
        cacheKey.append(GlobalConst.SEPARATOR).append(principal.getId());
        if (!msgLogService.validTempCode(cacheKey.toString(), String.valueOf(principal.getId())))
        { // 验证会话
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        if (StringUtils.isBlank(region) || StringUtils.isBlank(phone))
        { // 参数验证
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        if (!aliyunAFS.validParams(model))
        {// 验证滑块
            throw new BusinessException(CommonEnums.ERROR_AFS_VALID_FAILED);
        }
        if (null != accountService.findByPhone(region, phone))
        {// 判断手机号是否已存在
            throw new BusinessException(CommonEnums.ERROR_MOBILE_EXIST);
        }
        msgLogService.sendChangePhoneSMS(region, phone, GlobalConst.DEFAULT_LANG);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @ApiOperation(value = "更换手机号码", httpMethod = "POST")
    @RequestMapping(value = "/changePhone", method = RequestMethod.POST)
    public JsonMessage changePhone(String region, String phone, String valiCode) throws BusinessException
    {
        if (StringUtils.isBlank(region) || StringUtils.isBlank(phone))
        { // 参数验证
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (!msgLogService.validSMSCode(phone, valiCode))
        { // 验证手机
            throw new BusinessException(CommonEnums.ERROR_SMS_VALID_FAILED);
        }
        Account account = accountService.selectByPrimaryKey(principal.getId());
        account.setRegion(region);
        account.setMobile(phone);
        account.setUpdateDate(System.currentTimeMillis());
        accountService.updateByPrimaryKey(account);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @ApiOperation(value = "向当前邮箱地址发送验证码", httpMethod = "POST")
    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public JsonMessage sendMail(AliyunModel model) throws BusinessException
    {
        if (!aliyunAFS.validParams(model))
        {// 验证滑块
            throw new BusinessException(CommonEnums.ERROR_AFS_VALID_FAILED);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        msgLogService.sendMailCode(account.getEmail(), GlobalConst.DEFAULT_LANG);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @ApiOperation(value = "邮箱验证", httpMethod = "POST")
    @RequestMapping(value = "/checkMail", method = RequestMethod.POST)
    public JsonMessage checkMail(String valiCode) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (!msgLogService.validMailCode(account.getEmail(), valiCode))
        {// 验证邮箱
            throw new BusinessException(CommonEnums.ERROR_EMAIL_VALID_FAILED);
        }
        StringBuffer cacheKey = new StringBuffer(CacheConst.CACHE_CODE_PERFIX);
        cacheKey.append(GlobalConst.SEPARATOR).append(principal.getId());
        msgLogService.cacheTempCode(cacheKey.toString(), String.valueOf(principal.getId()));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @ApiOperation(value = "向新邮箱地址发送验证码", httpMethod = "POST")
    @RequestMapping(value = "/sendChangeMail", method = RequestMethod.POST)
    public JsonMessage sendChangeMail(String email, AliyunModel model) throws BusinessException
    {
        if (!aliyunAFS.validParams(model) || StringUtils.isBlank(email))
        {// 验证滑块
            throw new BusinessException(CommonEnums.ERROR_AFS_VALID_FAILED);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        StringBuffer cacheKey = new StringBuffer(CacheConst.CACHE_CODE_PERFIX);
        cacheKey.append(GlobalConst.SEPARATOR).append(principal.getId());
        if (!msgLogService.validTempCode(cacheKey.toString(), String.valueOf(principal.getId())))
        {// 验证会话
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        msgLogService.sendChangeMail(email, GlobalConst.DEFAULT_LANG);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @ApiOperation(value = "更换邮箱地址", httpMethod = "POST")
    @RequestMapping(value = "/changeMail", method = RequestMethod.POST)
    public JsonMessage changeMail(String email, String valiCode) throws BusinessException
    {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(valiCode))
        {// 验证滑块
            throw new BusinessException(CommonEnums.ERROR_AFS_VALID_FAILED);
        }
        if (!msgLogService.validMailCode(email, valiCode))
        {// 验证邮箱
            throw new BusinessException(CommonEnums.ERROR_EMAIL_VALID_FAILED);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        account.setEmail(email);
        account.setUpdateDate(System.currentTimeMillis());
        accountService.updateByPrimaryKey(account);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @ApiOperation(value = "修改登陆密码", httpMethod = "POST")
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public JsonMessage changePwd(String oldPwd, String newPwd, AliyunModel model) throws BusinessException
    {
        if (!aliyunAFS.validParams(model))
        {// 验证滑块
            throw new BusinessException(CommonEnums.ERROR_AFS_VALID_FAILED);
        }
        if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd))
        { // 验证参数
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountService.changePassword(principal.getId(), oldPwd, newPwd);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
