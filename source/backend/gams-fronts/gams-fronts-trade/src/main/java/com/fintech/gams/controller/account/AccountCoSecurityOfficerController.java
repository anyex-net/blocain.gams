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
import com.fintech.gams.account.entity.AccountCoSecurityOfficer;
import com.fintech.gams.account.service.AccountCoSecurityOfficerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公司安全官 控制器
 * <p>File：AccountCoSecurityOfficerController.java </p>
 * <p>Title: AccountCoSecurityOfficerController </p>
 * <p>Description:AccountCoSecurityOfficerController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoSecurityOfficer")
@Api(description = "公司安全官")
public class AccountCoSecurityOfficerController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoSecurityOfficerService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoSecurityOfficerService accountCoSecurityOfficerService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoSecurityOfficer:data")
    @ApiOperation(value = "根据ID取公司安全官", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoSecurityOfficerService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoSecurityOfficer:operator")
    @ApiOperation(value = "保存公司安全官", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoSecurityOfficer info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoSecurityOfficerService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoSecurityOfficer:data")
    @ApiOperation(value = "查询公司安全官", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoSecurityOfficer entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoSecurityOfficer> result = accountCoSecurityOfficerService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoSecurityOfficer:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoSecurityOfficerService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取公司安全官", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoSecurityOfficer accountCoSecurityOfficer = new AccountCoSecurityOfficer();
        accountCoSecurityOfficer.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoSecurityOfficerService.findList(accountCoSecurityOfficer));
    }

    @PostMapping(value = "/remove")
    @ApiOperation(value = "根据id删除公司安全官", httpMethod = "POST")
    public JsonMessage remove(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoSecurityOfficerService.remove(id));
    }
}
