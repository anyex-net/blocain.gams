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
import com.fintech.gams.account.entity.AccountParBrokerInfo;
import com.fintech.gams.account.service.AccountParBrokerInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 合伙企业经济商信息 控制器
 * <p>File：AccountParBrokerInfoController.java </p>
 * <p>Title: AccountParBrokerInfoController </p>
 * <p>Description:AccountParBrokerInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParBrokerInfo")
@Api(description = "合伙企业经济商监信息(第六页)")
public class AccountParBrokerInfoController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParBrokerInfoService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParBrokerInfoService accountParBrokerInfoService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParBrokerInfo:data")
    @ApiOperation(value = "根据ID取合伙企业经济商监信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParBrokerInfoService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParBrokerInfo:operator")
    @ApiOperation(value = "保存合伙企业经济商监信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParBrokerInfo info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountParBrokerInfoService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParBrokerInfo:data")
    @ApiOperation(value = "查询合伙企业经济商监信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParBrokerInfo entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountParBrokerInfo> result = accountParBrokerInfoService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParBrokerInfo:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountParBrokerInfoService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取合伙企业经济商监信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParBrokerInfo accountParBrokerInfo = new AccountParBrokerInfo();
        accountParBrokerInfo.setAccountId(principal.getId());
        return getJsonMessage(CommonEnums.SUCCESS,accountParBrokerInfoService.findList(accountParBrokerInfo));
    }
}
