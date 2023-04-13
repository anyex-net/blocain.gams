/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.account.entity.AccountCommonFile;
import com.fintech.gams.account.service.AccountCommonFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公共的文件 控制器
 * <p>File：AccountCommonFileController.java </p>
 * <p>Title: AccountCommonFileController </p>
 * <p>Description:AccountCommonFileController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCommonFile")
@Api(description = "公共的文件")
public class AccountCommonFileController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCommonFileService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCommonFileService accountCommonFileService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCommonFile:data")
    @ApiOperation(value = "根据ID取公共的文件", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCommonFileService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCommonFile:operator")
    @ApiOperation(value = "保存公共的文件", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCommonFile info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCommonFileService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCommonFile:data")
    @ApiOperation(value = "查询公共的文件", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCommonFile entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCommonFile> result = accountCommonFileService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCommonFile:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCommonFileService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
