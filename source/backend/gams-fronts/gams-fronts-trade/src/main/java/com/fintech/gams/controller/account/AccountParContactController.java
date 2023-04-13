/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.service.AccountCoPersonalService;
import com.fintech.gams.account.service.AccountParInstitutionalService;
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
import com.fintech.gams.account.service.AccountParContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 合伙企业联系信息 控制器
 * <p>File：AccountParContactController.java </p>
 * <p>Title: AccountParContactController </p>
 * <p>Description:AccountParContactController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParContact")
@Api(description = "合伙企业联系信息")
public class AccountParContactController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParContactService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParContactService accountParContactService;
    @Autowired(required = false)
    private AccountParInstitutionalService accountParInstitutionalService;
    @Autowired(required = false)
    private AccountParPersonalService accountParPersonalService;


    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParContact:data")
    @ApiOperation(value = "根据ID取合伙企业联系信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParContactService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParContact:operator")
    @ApiOperation(value = "保存合伙企业联系信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParContact info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountParContactService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParContact:data")
    @ApiOperation(value = "查询合伙企业联系信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParContact entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountParContact> result = accountParContactService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParContact:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountParContactService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId/institutional")
    @ApiOperation(value = "根据AccountId和合伙企业机构信息的id取合伙企业联系信息", httpMethod = "GET")
    public JsonMessage findByAccountIdAndInId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
        accountParInstitutional.setAccountId(principal.getId());
        // 查出机构信息
        List<AccountParInstitutional> parInstitutional = accountParInstitutionalService.findList(accountParInstitutional);
        if (parInstitutional.size() == 0) {
            return this.getJsonMessage(CommonEnums.SUCCESS);
        }
        //取合伙企业联系信息
        AccountParContact accountParContact = new AccountParContact();
        accountParContact.setAccountId(principal.getId());
        accountParContact.setParPersonalId(parInstitutional.get(0).getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParContactService.findList(accountParContact));
    }

    @GetMapping(value = "/findByAccountId/personal")
    @ApiOperation(value = "根据AccountId和合伙企业个人信息的id取合伙企业联系信息", httpMethod = "GET")
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
        //取合伙企业联系信息
        AccountParContact accountParContact = new AccountParContact();
        accountParContact.setAccountId(principal.getId());
        List<AccountParContact> parContactList = new ArrayList<>();
        for (AccountParPersonal parPersonal : accountParPersonalServiceList) {
            accountParContact.setParPersonalId(parPersonal.getId());
            parContactList = accountParContactService.findList(accountParContact);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, parContactList);
    }


}