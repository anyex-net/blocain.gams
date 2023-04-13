/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountParContact;
import com.fintech.gams.account.entity.AccountParPersonal;
import com.fintech.gams.account.service.AccountParPersonalService;
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
import com.fintech.gams.account.entity.AccountParIdentity;
import com.fintech.gams.account.service.AccountParIdentityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 合伙企业身份信息 控制器
 * <p>File：AccountParIdentityController.java </p>
 * <p>Title: AccountParIdentityController </p>
 * <p>Description:AccountParIdentityController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParIdentity")
@Api(description = "合伙企业身份信息")
public class AccountParIdentityController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParIdentityService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParIdentityService accountParIdentityService;
    @Autowired(required = false)
    private AccountParPersonalService accountParPersonalService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParIdentity:data")
    @ApiOperation(value = "根据ID取合伙企业身份信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParIdentityService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParIdentity:operator")
    @ApiOperation(value = "保存合伙企业身份信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParIdentity info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountParIdentityService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParIdentity:data")
    @ApiOperation(value = "查询合伙企业身份信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParIdentity entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountParIdentity> result = accountParIdentityService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParIdentity:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountParIdentityService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取合伙企业身份信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParIdentity accountParIdentity = new AccountParIdentity();
        accountParIdentity.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParIdentityService.findList(accountParIdentity));
    }

    @GetMapping(value = "/findByAccountId/personal")
    @ApiOperation(value = "根据AccountId和合伙企业个人信息的id取合伙企业身份信息", httpMethod = "GET")
    public JsonMessage findByAccountIdAndPerId(String personalType) throws BusinessException {
        if (null == personalType) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 查出合伙企业个人信息 根据 accountId 和 个人类型personalType
        AccountParPersonal accountParPersonal = new AccountParPersonal();
        accountParPersonal.setAccountId(principal.getId());
        accountParPersonal.setPersonalType(personalType);
        List<AccountParPersonal> accountParPersonalServiceList = accountParPersonalService.findList(accountParPersonal);
        //取合伙企业身份信息
        AccountParIdentity accountParIdentity = new AccountParIdentity();
        accountParIdentity.setAccountId(principal.getId());
        List<AccountParIdentity> parIdentities = new ArrayList<>();
        for (AccountParPersonal parPersonal : accountParPersonalServiceList) {
            accountParIdentity.setParPersonalId(parPersonal.getId());
            parIdentities = accountParIdentityService.findList(accountParIdentity);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, parIdentities);
    }

}
