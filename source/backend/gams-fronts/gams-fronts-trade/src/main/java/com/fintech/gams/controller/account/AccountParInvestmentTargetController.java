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
import com.fintech.gams.account.entity.AccountParInvestmentTarget;
import com.fintech.gams.account.service.AccountParInvestmentTargetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 合伙企业投资目标信息 控制器
 * <p>File：AccountParInvestmentTargetController.java </p>
 * <p>Title: AccountParInvestmentTargetController </p>
 * <p>Description:AccountParInvestmentTargetController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParInvestmentTarget")
@Api(description = "合伙企业投资目标信息")
public class AccountParInvestmentTargetController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParInvestmentTargetService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParInvestmentTargetService accountParInvestmentTargetService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParInvestmentTarget:data")
    @ApiOperation(value = "根据ID取合伙企业投资目标信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParInvestmentTargetService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParInvestmentTarget:operator")
    @ApiOperation(value = "保存合伙企业投资目标信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParInvestmentTarget info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountParInvestmentTargetService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParInvestmentTarget:data")
    @ApiOperation(value = "查询合伙企业投资目标信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParInvestmentTarget entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountParInvestmentTarget> result = accountParInvestmentTargetService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParInvestmentTarget:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountParInvestmentTargetService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取合伙企业投资目标信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParInvestmentTarget accountParInvestmentTarget = new AccountParInvestmentTarget();
        accountParInvestmentTarget.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParInvestmentTargetService.findList(accountParInvestmentTarget));
    }
}
