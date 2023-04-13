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
import com.fintech.gams.account.entity.AccountCoCrsEntityContr;
import com.fintech.gams.account.service.AccountCoCrsEntityContrService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公司crs实体（CRS-E）控权人信息 控制器
 * <p>File：AccountCoCrsEntityContrController.java </p>
 * <p>Title: AccountCoCrsEntityContrController </p>
 * <p>Description:AccountCoCrsEntityContrController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoCrsEntityContr")
@Api(description = "公司crs实体（CRS-E）控权人信息")
public class AccountCoCrsEntityContrController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoCrsEntityContrService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoCrsEntityContrService accountCoCrsEntityContrService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoCrsEntityContr:data")
    @ApiOperation(value = "根据ID取公司crs实体（CRS-E）控权人信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoCrsEntityContrService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoCrsEntityContr:operator")
    @ApiOperation(value = "保存公司crs实体（CRS-E）控权人信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoCrsEntityContr info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoCrsEntityContrService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoCrsEntityContr:data")
    @ApiOperation(value = "查询公司crs实体（CRS-E）控权人信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoCrsEntityContr entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoCrsEntityContr> result = accountCoCrsEntityContrService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoCrsEntityContr:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoCrsEntityContrService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId查询公司crs实体（CRS-E）控权人信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoCrsEntityContr accountCoCrsEntityContr = new AccountCoCrsEntityContr();
        accountCoCrsEntityContr.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoCrsEntityContrService.findList(accountCoCrsEntityContr));
    }
}
