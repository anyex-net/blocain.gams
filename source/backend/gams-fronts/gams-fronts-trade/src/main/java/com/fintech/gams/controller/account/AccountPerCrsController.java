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
import com.fintech.gams.account.entity.AccountPerCrs;
import com.fintech.gams.account.service.AccountPerCrsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 个人CRS 控制器
 * <p>File：AccountPerCrsController.java </p>
 * <p>Title: AccountPerCrsController </p>
 * <p>Description:AccountPerCrsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountPerCrs")
@Api(description = "个人CRS")
public class AccountPerCrsController extends GenericController
{
    @Autowired(required = false)
    private AccountPerCrsService accountPerCrsService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountPerCrs:data")
    @ApiOperation(value = "根据ID取个人CRS", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerCrsService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountPerCrs:operator")
    @ApiOperation(value = "保存个人CRS", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountPerCrs info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountPerCrsService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountPerCrs:data")
    @ApiOperation(value = "查询个人CRS", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountPerCrs entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountPerCrs> result = accountPerCrsService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountPerCrs:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountPerCrsService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }


    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据accountId取个人CRS", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountPerCrs accountPerCrs = new AccountPerCrs();
        accountPerCrs.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerCrsService.findList(accountPerCrs));
    }

}