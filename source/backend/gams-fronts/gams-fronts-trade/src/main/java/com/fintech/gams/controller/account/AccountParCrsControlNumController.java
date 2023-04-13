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
import com.fintech.gams.account.entity.AccountParCrsControlNum;
import com.fintech.gams.account.service.AccountParCrsControlNumService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 合伙企业CRS控权人自我证明控权人编号 控制器
 * <p>File：AccountParCrsControlNumController.java </p>
 * <p>Title: AccountParCrsControlNumController </p>
 * <p>Description:AccountParCrsControlNumController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParCrsControlNum")
@Api(description = "合伙企业CRS控权人自我证明控权人编号")
public class AccountParCrsControlNumController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParCrsControlNumService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParCrsControlNumService accountParCrsControlNumService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParCrsControlNum:data")
    @ApiOperation(value = "根据ID取合伙企业CRS控权人自我证明控权人编号", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParCrsControlNumService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParCrsControlNum:operator")
    @ApiOperation(value = "保存合伙企业CRS控权人自我证明控权人编号", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParCrsControlNum info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountParCrsControlNumService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParCrsControlNum:data")
    @ApiOperation(value = "查询合伙企业CRS控权人自我证明控权人编号", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParCrsControlNum entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountParCrsControlNum> result = accountParCrsControlNumService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParCrsControlNum:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountParCrsControlNumService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId和crs实体（CRS-E）控权人信息ID取合伙企业CRS控权人自我证明控权人编号", httpMethod = "GET")
    public JsonMessage findByAccountId(Long parCrsEntityContrId) throws BusinessException {
        if (null == parCrsEntityContrId){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParCrsControlNum accountParCrsControlNum = new AccountParCrsControlNum();
        accountParCrsControlNum.setAccountId(principal.getId());
        accountParCrsControlNum.setParCrsEntityContrId(parCrsEntityContrId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParCrsControlNumService.findList(accountParCrsControlNum));
    }
}
