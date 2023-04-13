/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountCoCrsEntityContr;
import com.fintech.gams.account.model.CompanyCrsEntityContrModel;
import com.fintech.gams.account.service.AccountCoCrsEntityContrService;
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
import com.fintech.gams.account.entity.AccountCoCrsEntity;
import com.fintech.gams.account.service.AccountCoCrsEntityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 公司CRS实体自我证明表格 控制器
 * <p>File：AccountCoCrsEntityController.java </p>
 * <p>Title: AccountCoCrsEntityController </p>
 * <p>Description:AccountCoCrsEntityController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoCrsEntity")
@Api(description = "公司CRS实体自我证明表格")
public class AccountCoCrsEntityController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoCrsEntityService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoCrsEntityService accountCoCrsEntityService;

    @Autowired(required = false)
    private AccountCoCrsEntityContrService accountCoCrsEntityContrService;


    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoCrsEntity:data")
    @ApiOperation(value = "根据ID取公司CRS实体自我证明表格", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoCrsEntityService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoCrsEntity:operator")
    @ApiOperation(value = "保存公司CRS实体自我证明表格", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoCrsEntity info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoCrsEntityService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoCrsEntity:data")
    @ApiOperation(value = "查询公司CRS实体自我证明表格", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoCrsEntity entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoCrsEntity> result = accountCoCrsEntityService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoCrsEntity:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoCrsEntityService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取CRS实体自我证明表格结果集", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoCrsEntity accountCoCrsEntity = new AccountCoCrsEntity();
        accountCoCrsEntity.setAccountId(principal.getId());
        List<AccountCoCrsEntity> accountCoCrsEntityList = accountCoCrsEntityService.findList(accountCoCrsEntity);
        // 公司crs实体（CRS-E）控权人信息
        AccountCoCrsEntityContr accountCoCrsEntityContr = new AccountCoCrsEntityContr();
        accountCoCrsEntityContr.setAccountId(principal.getId());
        CompanyCrsEntityContrModel model = new CompanyCrsEntityContrModel();
        for (AccountCoCrsEntity coCrsEntity : accountCoCrsEntityList) {
            model.setAccountCoCrsEntity(coCrsEntity);
        }
        List<AccountCoCrsEntityContr> accountCoCrsEntityContrList = accountCoCrsEntityContrService.findList(accountCoCrsEntityContr);
        model.setAccountCoCrsEntityContrList(accountCoCrsEntityContrList);
        if (accountCoCrsEntityList.size() == 0) {
            return this.getJsonMessage(CommonEnums.SUCCESS, "");
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, model);
    }

    @GetMapping(value = "/findCrsEntity")
    @ApiOperation(value = "根据AccountId取CRS实体自我证明表格", httpMethod = "GET")
    public JsonMessage findCrsEntity() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoCrsEntity accountCoCrsEntity = new AccountCoCrsEntity();
        accountCoCrsEntity.setAccountId(principal.getId());
        List<AccountCoCrsEntity> accountCoCrsEntityList = accountCoCrsEntityService.findList(accountCoCrsEntity);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoCrsEntityList);
    }
}
