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
import com.fintech.gams.account.entity.AccountPerRegulatory;
import com.fintech.gams.account.service.AccountPerRegulatoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 个人账户规管信息 控制器
 * <p>File：AccountPerRegulatoryController.java </p>
 * <p>Title: AccountPerRegulatoryController </p>
 * <p>Description:AccountPerRegulatoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountPerRegulatory")
@Api(description = "个人账户规管信息")
public class AccountPerRegulatoryController extends GenericController
{
    @Autowired(required = false)
    private AccountPerRegulatoryService accountPerRegulatoryService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountPerRegulatory:data")
    @ApiOperation(value = "根据ID取个人账户规管信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerRegulatoryService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountPerRegulatory:operator")
    @ApiOperation(value = "保存个人账户规管信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountPerRegulatory info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountPerRegulatoryService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountPerRegulatory:data")
    @ApiOperation(value = "查询个人账户规管信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountPerRegulatory entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountPerRegulatory> result = accountPerRegulatoryService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountPerRegulatory:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountPerRegulatoryService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据accountId取个人账户规管信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountPerRegulatory accountPerRegulatory = new AccountPerRegulatory();
        accountPerRegulatory.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerRegulatoryService.findList(accountPerRegulatory));
    }
}
