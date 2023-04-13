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
import com.fintech.gams.account.entity.AccountPerAddress;
import com.fintech.gams.account.service.AccountPerAddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 个人地址 控制器
 * <p>File：AccountPerAddressController.java </p>
 * <p>Title: AccountPerAddressController </p>
 * <p>Description:AccountPerAddressController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountPerAddress")
@Api(description = "个人地址")
public class AccountPerAddressController extends GenericController
{
    @Autowired(required = false)
    private AccountPerAddressService accountPerAddressService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountPerAddress:data")
    @ApiOperation(value = "根据ID取个人地址", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerAddressService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountPerAddress:operator")
    @ApiOperation(value = "保存个人地址", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountPerAddress info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountPerAddressService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountPerAddress:data")
    @ApiOperation(value = "查询个人地址", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountPerAddress entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountPerAddress> result = accountPerAddressService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountPerAddress:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountPerAddressService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }


    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据accountId取个人地址", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountPerAddress accountPerAddress = new AccountPerAddress();
        accountPerAddress.setAccountId(principal.getId());
        accountPerAddress.setDelFlag(false);
        return getJsonMessage(CommonEnums.SUCCESS, accountPerAddressService.findList(accountPerAddress));
    }
}
