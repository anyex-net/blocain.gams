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
import com.fintech.gams.account.entity.AccountCoTaxClassification;
import com.fintech.gams.account.service.AccountCoTaxClassificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公司税收分类 控制器
 * <p>File：AccountCoTaxClassificationController.java </p>
 * <p>Title: AccountCoTaxClassificationController </p>
 * <p>Description:AccountCoTaxClassificationController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoTaxClassification")
@Api(description = "公司税收分类")
public class AccountCoTaxClassificationController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoTaxClassificationService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoTaxClassificationService accountCoTaxClassificationService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoTaxClassification:data")
    @ApiOperation(value = "根据ID取公司税收分类", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoTaxClassificationService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoTaxClassification:operator")
    @ApiOperation(value = "保存公司税收分类", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoTaxClassification info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoTaxClassificationService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoTaxClassification:data")
    @ApiOperation(value = "查询公司税收分类", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoTaxClassification entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoTaxClassification> result = accountCoTaxClassificationService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoTaxClassification:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoTaxClassificationService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取公司税收分类", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException{
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoTaxClassificationService.findByAccountId(principal.getId()));
    }
}
