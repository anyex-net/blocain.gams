/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountPerAgreement;
import com.fintech.gams.account.service.AccountPerAgreementService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 个人协议纰漏 控制器
 * <p>File：AccountPerAgreementController.java </p>
 * <p>Title: AccountPerAgreementController </p>
 * <p>Description:AccountPerAgreementController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountPerAgreement")
@Api(description = "个人协议纰漏")
public class AccountPerAgreementController extends GenericController
{
    @Autowired(required = false)
    private AccountPerAgreementService accountPerAgreementService;


    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountPerAgreement:data")
    @ApiOperation(value = "根据ID取个人协议纰漏", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerAgreementService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountPerAgreement:operator")
    @ApiOperation(value = "保存个人协议纰漏", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountPerAgreement info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountPerAgreementService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountPerAgreement:data")
    @ApiOperation(value = "查询个人协议纰漏", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountPerAgreement entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountPerAgreement> result = accountPerAgreementService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountPerAgreement:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountPerAgreementService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据accountId取个人协议纰漏", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountPerAgreement accountPerAgreement = new AccountPerAgreement();
        accountPerAgreement.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerAgreementService.findList(accountPerAgreement));
    }

}
