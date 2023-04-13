package com.fintech.gams.controller;

import com.fintech.gams.consts.GlobalConst;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.service.AccountService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.common.service.MsgLogService;
import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.enums.AccountEnums;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.AccountToken;
import com.fintech.gams.utils.RedisUtils;
import com.fintech.gams.utils.StringUtils;

import io.swagger.annotations.ApiOperation;

/**
 * CommonController
 * <p>File: CommonController.java </p>
 * <p>Title: CommonController </p>
 * <p>Description: CommonController </p>
 * <p>Copyright: Copyright (c) 2019-07-08</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
public class CommonController extends GenericController
{
    @Autowired(required = false)
    private AccountService accountService;
    
    @Autowired(required = false)
    private MsgLogService  msgLogService;
    
    @RequestMapping("index")
    public String index()
    {
        return "index";
    }
    
    @RequestMapping("unauthorized")
    public String unauthorized()
    {
        return "unauthorized";
    }
    
    @RequestMapping("/logout")
    public String logout() throws Exception
    {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject)
        {
            subject.logout();
        }
        return "redirect:/";
    }
    
    @ResponseBody
    @PostMapping(value = "/oauth/sendSms")
    @ApiOperation(value = "半登陆状态下发送验证码", httpMethod = "POST")
    public JsonMessage sendLoginSms() throws BusinessException
    {
        Subject subject = SecurityUtils.getSubject();
        StringBuffer key = new StringBuffer(CacheConst.LOGIN_PERFIX).append(subject.getSession().getId());
        AccountToken token = (AccountToken) RedisUtils.getObject(key.toString());
        if (null == token) return this.getJsonMessage(CommonEnums.ERROR_SESSION_TIME_OUT);
        Account account = accountService.selectByPrimaryKey(token.getId());
        if (StringUtils.isBlank(account.getCountry()) || StringUtils.isBlank(account.getMobile()))
        {// 判断手机号有没有绑定
            throw new BusinessException(AccountEnums.ACCOUNT_PHONE_NOTBIND);
        }
        msgLogService.sendBindPhoneSMS(account.getRegion(), account.getMobile(), GlobalConst.DEFAULT_LANG);
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }
}
