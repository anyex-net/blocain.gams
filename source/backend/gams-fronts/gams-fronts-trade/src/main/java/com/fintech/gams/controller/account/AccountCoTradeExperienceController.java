/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.model.CompanyTradeExperienceModel;
import com.fintech.gams.account.service.AccountCommonTradeLocationService;
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
import com.fintech.gams.account.service.AccountCoTradeExperienceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.*;

/**
 * 公司交易经验信息 控制器
 * <p>File：AccountCoTradeExperienceController.java </p>
 * <p>Title: AccountCoTradeExperienceController </p>
 * <p>Description:AccountCoTradeExperienceController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoTradeExperience")
@Api(description = "公司交易经验信息")
public class AccountCoTradeExperienceController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoTradeExperienceService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoTradeExperienceService accountCoTradeExperienceService;

    @Autowired(required = false)
    private AccountCommonTradeLocationService accountCommonTradeLocationService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoTradeExperience:data")
    @ApiOperation(value = "根据ID取公司交易经验信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoTradeExperienceService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoTradeExperience:operator")
    @ApiOperation(value = "保存公司交易经验信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoTradeExperience info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoTradeExperienceService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoTradeExperience:data")
    @ApiOperation(value = "查询公司交易经验信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoTradeExperience entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoTradeExperience> result = accountCoTradeExperienceService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoTradeExperience:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoTradeExperienceService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取公司交易经验信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoTradeExperience accountCoTradeExperience = new AccountCoTradeExperience();
        accountCoTradeExperience.setAccountId(principal.getId());
        //交易经验
        List<AccountCoTradeExperience> accountCoTradeExperienceList = accountCoTradeExperienceService.findList(accountCoTradeExperience);
        List<AccountCommonTradeLocation> accountCommonTradeLocations = new ArrayList<>();
        AccountCommonTradeLocation accountCommonTradeLocation = new AccountCommonTradeLocation();
        for (AccountCoTradeExperience coTradeExperience : accountCoTradeExperienceList) {
            accountCommonTradeLocation.setTansactionType(coTradeExperience.getTansactionType());
            List<String> locationStrsList = Arrays.asList(coTradeExperience.getTradingLocation().split(","));
            for (String locationStr : locationStrsList) {
                accountCommonTradeLocation.setTansactionLocation(locationStr);
                accountCommonTradeLocations.addAll(accountCommonTradeLocationService.findList(accountCommonTradeLocation));
            }
        }
        // 结果集
        CompanyTradeExperienceModel model = new CompanyTradeExperienceModel();
        model.setAccountCoTradeExperienceList(accountCoTradeExperienceList);
        model.setAccountCommonTradeLocationList(accountCommonTradeLocations);
        return this.getJsonMessage(CommonEnums.SUCCESS, model);
    }

}



