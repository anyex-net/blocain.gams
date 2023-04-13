/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountCoInstitutional;
import com.fintech.gams.account.entity.AccountCoPersonal;
import com.fintech.gams.account.entity.AccountCoTaxes;
import com.fintech.gams.account.service.AccountCoInstitutionalService;
import com.fintech.gams.account.service.AccountCoPersonalService;
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
import com.fintech.gams.account.entity.AccountCoIdentity;
import com.fintech.gams.account.service.AccountCoIdentityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司身份信息 控制器
 * <p>File：AccountCoIdentityController.java </p>
 * <p>Title: AccountCoIdentityController </p>
 * <p>Description:AccountCoIdentityController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoIdentity")
@Api(description = "公司身份信息")
public class AccountCoIdentityController extends GenericController {
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoIdentityService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoIdentityService accountCoIdentityService;


    @Autowired(required = false)
    private AccountCoPersonalService accountCoPersonalService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoIdentity:data")
    @ApiOperation(value = "根据ID取公司身份信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoIdentityService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoIdentity:operator")
    @ApiOperation(value = "保存公司身份信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoIdentity info) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info)) {
            accountCoIdentityService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoIdentity:data")
    @ApiOperation(value = "查询公司身份信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoIdentity entity, @ModelAttribute Pagination pagin) throws BusinessException {
        PaginateResult<AccountCoIdentity> result = accountCoIdentityService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoIdentity:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException {
        accountCoIdentityService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取公司身份信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoIdentityService.findByAccountId(principal.getId()));
    }

    @GetMapping(value = "/findByAccountId/personal")
    @ApiOperation(value = "根据AccountId和公司个人信息的id取公司身份信息", httpMethod = "GET")
    public JsonMessage findByAccountIdAndPerId(String personalType) throws BusinessException {
        if (null == personalType) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 查出公司个人信息 根据 accountId 和 个人类型personalType
        AccountCoPersonal accountCoPersonal = new AccountCoPersonal();
        accountCoPersonal.setAccountId(principal.getId());
        accountCoPersonal.setPersonalType(personalType);
        List<AccountCoPersonal> accountCoPersonalList = accountCoPersonalService.findList(accountCoPersonal);
        //取公司身份信息
        AccountCoIdentity accountCoIdentity = new AccountCoIdentity();
        accountCoIdentity.setAccountId(principal.getId());
        List<AccountCoIdentity> coIdentityList = new ArrayList<>();
        for (AccountCoPersonal coPersonal : accountCoPersonalList) {
            accountCoIdentity.setCoPersonalId(coPersonal.getId());
            coIdentityList = accountCoIdentityService.findList(accountCoIdentity);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, coIdentityList);
    }
}
