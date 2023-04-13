/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.account.service.AccountCommonTradeLocationService;
import com.fintech.gams.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * ACCOUNT_COMMON_TRADE_LOCATION 控制器
 * <p>File：AccountCommonTradeLocationController.java </p>
 * <p>Title: AccountCommonTradeLocationController </p>
 * <p>Description:AccountCommonTradeLocationController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCommonTradeLocation")
@Api(description = "账户交易经验地点")
public class AccountCommonTradeLocationController extends GenericController
{
    @Autowired(required = false)
    private AccountCommonTradeLocationService accountCommonTradeLocationService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCommonTradeLocation:data")
    @ApiOperation(value = "根据ID取 账户交易经验地点", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCommonTradeLocationService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCommonTradeLocation:operator")
    @ApiOperation(value = "保存 账户交易经验地点", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCommonTradeLocation info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCommonTradeLocationService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCommonTradeLocation:data")
    @ApiOperation(value = "查询 账户交易经验地点", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCommonTradeLocation entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCommonTradeLocation> result = accountCommonTradeLocationService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCommonTradeLocation:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCommonTradeLocationService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByTansactionType")
    @ApiOperation(value = "根据TansactionType(交易类型)取 账户交易经验地点", httpMethod = "GET")
    public JsonMessage findByTansactionType(String tansactionType) throws BusinessException
    {
        if (StringUtils.isBlank(tansactionType))
        { // 参数判断
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCommonTradeLocationService.findByTansactionType(tansactionType));
    }

}
