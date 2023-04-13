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
import com.fintech.gams.account.entity.AccountCoCrsControl;
import com.fintech.gams.account.service.AccountCoCrsControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公司CRS控权人自我证明表格 控制器
 * <p>File：AccountCoCrsControlController.java </p>
 * <p>Title: AccountCoCrsControlController </p>
 * <p>Description:AccountCoCrsControlController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoCrsControl")
@Api(description = "公司CRS控权人自我证明表格")
public class AccountCoCrsControlController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoCrsControlService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoCrsControlService accountCoCrsControlService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoCrsControl:data")
    @ApiOperation(value = "根据ID取公司CRS控权人自我证明表格", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoCrsControlService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoCrsControl:operator")
    @ApiOperation(value = "保存公司CRS控权人自我证明表格", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoCrsControl info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoCrsControlService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoCrsControl:data")
    @ApiOperation(value = "查询公司CRS控权人自我证明表格", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoCrsControl entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoCrsControl> result = accountCoCrsControlService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoCrsControl:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoCrsControlService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId和crs实体（CRS-E）控权人信息ID取CRS控权人自我证明表格", httpMethod = "GET")
    public JsonMessage findByAccountId(Long coCrsEntityContrId) throws BusinessException {
        if (null == coCrsEntityContrId){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoCrsControl accountCoCrsControl = new AccountCoCrsControl();
        accountCoCrsControl.setAccountId(principal.getId());
        accountCoCrsControl.setCoCrsEntityContrId(coCrsEntityContrId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoCrsControlService.findList(accountCoCrsControl));
    }
}
