/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.account.entity.AccountPerInvestAccount;
import com.fintech.gams.account.service.AccountPerInvestAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 个人一体化投资管理账户功能 控制器
 * <p>File：AccountPerInvestAccountController.java </p>
 * <p>Title: AccountPerInvestAccountController </p>
 * <p>Description:AccountPerInvestAccountController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountPerInvestAccount")
@Api(description = "个人一体化投资管理账户功能")
public class AccountPerInvestAccountController extends GenericController
{
    @Autowired(required = false)
    private AccountPerInvestAccountService accountPerInvestAccountService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountPerInvestAccount:data")
    @ApiOperation(value = "根据ID取个人一体化投资管理账户功能", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerInvestAccountService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountPerInvestAccount:operator")
    @ApiOperation(value = "保存个人一体化投资管理账户功能", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountPerInvestAccount info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountPerInvestAccountService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountPerInvestAccount:data")
    @ApiOperation(value = "查询个人一体化投资管理账户功能", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountPerInvestAccount entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountPerInvestAccount> result = accountPerInvestAccountService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountPerInvestAccount:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountPerInvestAccountService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据 accountId 取个人一体化投资管理账户功能", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountPerInvestAccount accountPerInvestAccount = new AccountPerInvestAccount();
        accountPerInvestAccount.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerInvestAccountService.findList(accountPerInvestAccount));
    }
}
