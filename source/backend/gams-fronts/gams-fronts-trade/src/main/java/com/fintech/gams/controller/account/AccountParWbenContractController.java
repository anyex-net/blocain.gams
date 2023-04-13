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
import com.fintech.gams.account.entity.AccountParWbenContract;
import com.fintech.gams.account.service.AccountParWbenContractService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 合伙企业w8ben表 控制器
 * <p>File：AccountParWbenContractController.java </p>
 * <p>Title: AccountParWbenContractController </p>
 * <p>Description:AccountParWbenContractController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParWbenContract")
@Api(description = "合伙企业w8ben表")
public class AccountParWbenContractController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParWbenContractService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParWbenContractService accountParWbenContractService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParWbenContract:data")
    @ApiOperation(value = "根据ID取合伙企业w8ben表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParWbenContractService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParWbenContract:operator")
    @ApiOperation(value = "保存合伙企业w8ben表", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParWbenContract info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountParWbenContractService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParWbenContract:data")
    @ApiOperation(value = "查询合伙企业w8ben表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParWbenContract entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountParWbenContract> result = accountParWbenContractService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParWbenContract:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountParWbenContractService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId和类型取合伙企业w8ben表", httpMethod = "GET")
    public JsonMessage findByAccountId(String type) throws BusinessException {
        if (null == type) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParWbenContract accountParWbenContract = new AccountParWbenContract();
        accountParWbenContract.setAccountId(principal.getId());
        accountParWbenContract.setType(type);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParWbenContractService.findList(accountParWbenContract));
    }
}
