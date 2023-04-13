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
import com.fintech.gams.account.entity.AccountParWealthSource;
import com.fintech.gams.account.service.AccountParWealthSourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 合伙企业财富来源 控制器
 * <p>File：AccountParWealthSourceController.java </p>
 * <p>Title: AccountParWealthSourceController </p>
 * <p>Description:AccountParWealthSourceController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParWealthSource")
@Api(description = "合伙企业财富来源")
public class AccountParWealthSourceController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParWealthSourceService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParWealthSourceService accountParWealthSourceService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParWealthSource:data")
    @ApiOperation(value = "根据ID取合伙企业财富来源", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParWealthSourceService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParWealthSource:operator")
    @ApiOperation(value = "保存合伙企业财富来源", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParWealthSource info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountParWealthSourceService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParWealthSource:data")
    @ApiOperation(value = "查询合伙企业财富来源", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParWealthSource entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountParWealthSource> result = accountParWealthSourceService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParWealthSource:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountParWealthSourceService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取合伙企业财富来源", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParWealthSource accountParWealthSource = new AccountParWealthSource();
        accountParWealthSource.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParWealthSourceService.findList(accountParWealthSource));
    }
}
