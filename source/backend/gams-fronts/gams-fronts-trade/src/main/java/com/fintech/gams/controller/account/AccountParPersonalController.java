/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountCoPersonal;
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
import com.fintech.gams.account.entity.AccountParPersonal;
import com.fintech.gams.account.service.AccountParPersonalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 合伙企业个人信息 控制器
 * <p>File：AccountParPersonalController.java </p>
 * <p>Title: AccountParPersonalController </p>
 * <p>Description:AccountParPersonalController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParPersonal")
@Api(description = "合伙企业个人信息")
public class AccountParPersonalController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParPersonalService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParPersonalService accountParPersonalService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParPersonal:data")
    @ApiOperation(value = "根据ID取合伙企业个人信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParPersonalService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParPersonal:operator")
    @ApiOperation(value = "保存合伙企业个人信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParPersonal info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountParPersonalService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParPersonal:data")
    @ApiOperation(value = "查询合伙企业个人信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParPersonal entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountParPersonal> result = accountParPersonalService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParPersonal:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountParPersonalService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId和合伙企业个人类型取合伙企业个人信息", httpMethod = "GET")
    public JsonMessage findByAccountId(String personalType,String personalTypeTwo) throws BusinessException {
        if (null == personalType) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParPersonal accountParPersonal = new AccountParPersonal();
        accountParPersonal.setAccountId(principal.getId());
        if (null != personalTypeTwo) {
            accountParPersonal.setPersonalTypeTwo(personalTypeTwo);
        } else {
            accountParPersonal.setPersonalType(personalType);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParPersonalService.findList(accountParPersonal));
    }
}
