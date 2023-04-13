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
import com.fintech.gams.account.entity.AccountCoWbenContract;
import com.fintech.gams.account.service.AccountCoWbenContractService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公司w8ben表 控制器
 * <p>File：AccountCoWbenContractController.java </p>
 * <p>Title: AccountCoWbenContractController </p>
 * <p>Description:AccountCoWbenContractController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoWbenContract")
@Api(description = "公司w8ben表")
public class AccountCoWbenContractController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoWbenContractService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoWbenContractService accountCoWbenContractService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoWbenContract:data")
    @ApiOperation(value = "根据ID取公司w8ben表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoWbenContractService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoWbenContract:operator")
    @ApiOperation(value = "保存公司w8ben表", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoWbenContract info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoWbenContractService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoWbenContract:data")
    @ApiOperation(value = "查询公司w8ben表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoWbenContract entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoWbenContract> result = accountCoWbenContractService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoWbenContract:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoWbenContractService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId和类型取公司w8ben表", httpMethod = "GET")
    public JsonMessage findByAccountId(String type) throws BusinessException {
        if (null == type) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoWbenContract accountCoWbenContract = new AccountCoWbenContract();
        accountCoWbenContract.setAccountId(principal.getId());
        accountCoWbenContract.setType(type);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoWbenContractService.findList(accountCoWbenContract));
    }
}
