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
import com.fintech.gams.account.entity.AccountCoAmlCtf;
import com.fintech.gams.account.service.AccountCoAmlCtfService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公司AML/CTF风险问卷 控制器
 * <p>File：AccountCoAmlCtfController.java </p>
 * <p>Title: AccountCoAmlCtfController </p>
 * <p>Description:AccountCoAmlCtfController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoAmlCtf")
@Api(description = "公司AML/CTF风险问卷")
public class AccountCoAmlCtfController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoAmlCtfService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoAmlCtfService accountCoAmlCtfService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoAmlCtf:data")
    @ApiOperation(value = "根据ID取公司AML/CTF风险问卷", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoAmlCtfService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoAmlCtf:operator")
    @ApiOperation(value = "保存公司AML/CTF风险问卷", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoAmlCtf info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoAmlCtfService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoAmlCtf:data")
    @ApiOperation(value = "查询公司AML/CTF风险问卷", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoAmlCtf entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoAmlCtf> result = accountCoAmlCtfService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoAmlCtf:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoAmlCtfService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取公司AML/CTF风险问卷", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoAmlCtf accountCoAmlCtf = new AccountCoAmlCtf();
        accountCoAmlCtf.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoAmlCtfService.findList(accountCoAmlCtf));
    }
}
