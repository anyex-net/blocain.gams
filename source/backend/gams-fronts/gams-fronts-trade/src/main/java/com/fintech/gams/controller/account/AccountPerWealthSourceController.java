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
import com.fintech.gams.account.entity.AccountPerWealthSource;
import com.fintech.gams.account.service.AccountPerWealthSourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 个人信息 控制器
 * <p>File：AccountPerWealthSourceController.java </p>
 * <p>Title: AccountPerWealthSourceController </p>
 * <p>Description:AccountPerWealthSourceController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountPerWealthSource")
@Api(description = "财富来源")
public class AccountPerWealthSourceController extends GenericController
{
    @Autowired(required = false)
    private AccountPerWealthSourceService accountPerWealthSourceService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountPerWealthSource:data")
    @ApiOperation(value = "根据ID取财富来源", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerWealthSourceService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountPerWealthSource:operator")
    @ApiOperation(value = "保存财富来源", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountPerWealthSource info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountPerWealthSourceService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountPerWealthSource:data")
    @ApiOperation(value = "查询财富来源", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountPerWealthSource entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountPerWealthSource> result = accountPerWealthSourceService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountPerWealthSource:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountPerWealthSourceService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据accountId取财富来源", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountPerWealthSource accountPerWealthSource =new AccountPerWealthSource();
        accountPerWealthSource.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerWealthSourceService.findList(accountPerWealthSource));
    }
}
