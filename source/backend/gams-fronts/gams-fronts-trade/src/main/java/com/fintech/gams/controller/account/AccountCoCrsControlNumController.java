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
import com.fintech.gams.account.entity.AccountCoCrsControlNum;
import com.fintech.gams.account.service.AccountCoCrsControlNumService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公司CRS控权人自我证明控权人编号 控制器
 * <p>File：AccountCoCrsControlNumController.java </p>
 * <p>Title: AccountCoCrsControlNumController </p>
 * <p>Description:AccountCoCrsControlNumController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoCrsControlNum")
@Api(description = "公司CRS控权人自我证明控权人编号")
public class AccountCoCrsControlNumController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoCrsControlNumService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoCrsControlNumService accountCoCrsControlNumService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoCrsControlNum:data")
    @ApiOperation(value = "根据ID取公司CRS控权人自我证明控权人编号", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoCrsControlNumService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoCrsControlNum:operator")
    @ApiOperation(value = "保存公司CRS控权人自我证明控权人编号", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoCrsControlNum info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoCrsControlNumService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoCrsControlNum:data")
    @ApiOperation(value = "查询公司CRS控权人自我证明控权人编号", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoCrsControlNum entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoCrsControlNum> result = accountCoCrsControlNumService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoCrsControlNum:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoCrsControlNumService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId和crs实体（CRS-E）控权人信息ID取CRS控权人自我证明控权人编号", httpMethod = "GET")
    public JsonMessage findByAccountId(Long coCrsEntityContrId) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == coCrsEntityContrId){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountCoCrsControlNum accountCoCrsControlNum = new AccountCoCrsControlNum();
        accountCoCrsControlNum.setAccountId(principal.getId());
        accountCoCrsControlNum.setCoCrsEntityContrId(coCrsEntityContrId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoCrsControlNumService.findList(accountCoCrsControlNum));
    }
}
