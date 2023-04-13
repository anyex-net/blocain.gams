package com.fintech.gams.controller.account;

import com.fintech.gams.account.consts.AccountConsts;
import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.model.CheckDuplicateNameModel;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.NetworkUtils;
import com.fintech.gams.utils.SerialnoUtils;
import com.fintech.gams.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import com.fintech.gams.account.service.AccountService;
import com.fintech.gams.bean.GenericController;
import javax.servlet.http.HttpServletRequest;

/**
 * 注册控制器
 * <p>File: RegisterController.java </p>
 * <p>Title: RegisterController </p>
 * <p>Description: RegisterController </p>
 * <p>Copyright: Copyright (c) 2019-05-29</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@EnableAsync
@RestController
@Api(description = "注册控制器")
@RequestMapping(GlobalConst.REGISTER)
public class RegisterController extends GenericController
{

    @Autowired(required = false)
    private AccountService accountService;

    @ApiOperation(value = "验证账户名/手机号/邮箱是否重复", httpMethod = "POST")
    @PostMapping("/checkDuplicateName")
    public JsonMessage checkDuplicateName(@RequestBody CheckDuplicateNameModel model) throws BusinessException
    {//判断账户是否重复
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        model.setLang(StringUtils.isBlank(model.getLang()) ? GlobalConst.DEFAULT_LANG : model.getLang());
        if (beanValidator(json, model)) {
            if (model.getType().equals(AccountConsts.ACCOUNT_NAME)) {
                if (!accountService.checkDuplicateName(model.getValue())) {
                    if (model.getLang().equals(GlobalConst.DEFAULT_LANG)) {
                        throw new BusinessException(CommonEnums.ERROR_ACCOUNT_EXIST);
                    } else {
                        throw new BusinessException(2010, "账户已经存在");
                    }
                }
            }// 手机号重复(区号不一样可以手机号一样)
            if (model.getType().equals(AccountConsts.MOBILE)) {
                if (!accountService.checkDuplicateMobile(model.getValue(), model.getRegion())) {
                    if (model.getLang().equals(GlobalConst.DEFAULT_LANG)) {
                        throw new BusinessException(CommonEnums.ERROR_MOBILE_EXIST);
                    } else {
                        throw new BusinessException(2010, "手机号码已经存在");
                    }
                }
            }// 邮箱重复
            if (model.getType().equals(AccountConsts.EMAIL)) {
                if (!accountService.checkDuplicateEmail(model.getValue())) {
                    if (model.getLang().equals(GlobalConst.DEFAULT_LANG)) {
                        throw new BusinessException(CommonEnums.ERROR_EMAIL_EXIST);
                    } else {
                        throw new BusinessException(2010, "电子邮件已经存在");
                    }
                }
            }
        }
        return json;
    }

    @ApiOperation(value = "账户注册", httpMethod = "POST")
    @PostMapping("/createAccount")
    public JsonMessage registerAccount(HttpServletRequest request, @RequestBody Account account) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json,account))
        {
            // 激活码
            account.setActivationCode(SerialnoUtils.buildUUID());
            // ip 地址
            account.setLocation(NetworkUtils.getIpAddr(request));
            accountService.createAccount(account);
        }
        return json;
    }

    @ApiOperation(value = "账户注册激活码激活 -1：未激活 0：已激活", httpMethod = "POST")
    @PostMapping("/accountActivation")
    public JsonMessage activationAccount(String code) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (null == code) {
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        accountService.accountActivation(code);
        return json;
    }
}
