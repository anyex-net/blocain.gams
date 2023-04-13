/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.account.model.PartnerShipTradeExperienceModel;
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
import com.fintech.gams.account.entity.AccountParTradeExperience;
import com.fintech.gams.account.service.AccountParTradeExperienceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合伙企业交易经验信息 控制器
 * <p>File：AccountParTradeExperienceController.java </p>
 * <p>Title: AccountParTradeExperienceController </p>
 * <p>Description:AccountParTradeExperienceController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParTradeExperience")
@Api(description = "合伙企业交易经验信息")
public class AccountParTradeExperienceController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParTradeExperienceService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParTradeExperienceService accountParTradeExperienceService;
    @Autowired(required = false)
    private AccountCommonTradeLocationService accountCommonTradeLocationService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParTradeExperience:data")
    @ApiOperation(value = "根据ID取合伙企业交易经验信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParTradeExperienceService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParTradeExperience:operator")
    @ApiOperation(value = "保存合伙企业交易经验信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParTradeExperience info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountParTradeExperienceService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParTradeExperience:data")
    @ApiOperation(value = "查询合伙企业交易经验信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParTradeExperience entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountParTradeExperience> result = accountParTradeExperienceService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParTradeExperience:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountParTradeExperienceService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取合伙企业交易经验信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParTradeExperience accountParTradeExperience = new AccountParTradeExperience();
        accountParTradeExperience.setAccountId(principal.getId());
        // 合伙企业交易经验
        List<AccountParTradeExperience> accountParTradeExperienceList = accountParTradeExperienceService.findList(accountParTradeExperience);
        List<AccountCommonTradeLocation> accountCommonTradeLocations = new ArrayList<>();
        AccountCommonTradeLocation accountCommonTradeLocation = new AccountCommonTradeLocation();
        for (AccountParTradeExperience parTradeExperience : accountParTradeExperienceList) {
            accountCommonTradeLocation.setTansactionType(parTradeExperience.getTansactionType());
            List<String> locationStrsList = Arrays.asList(parTradeExperience.getTradingLocation().split(","));
            for (String locationStr : locationStrsList) {
                accountCommonTradeLocation.setTansactionLocation(locationStr);
                accountCommonTradeLocations.addAll(accountCommonTradeLocationService.findList(accountCommonTradeLocation));
            }
        }
        // 结果集
        PartnerShipTradeExperienceModel model = new PartnerShipTradeExperienceModel();
        model.setAccountParTradeExperienceList(accountParTradeExperienceList);
        model.setAccountCommonTradeLocationList(accountCommonTradeLocations);
        if (model.getAccountCommonTradeLocationList().size() == 0 || model.getAccountParTradeExperienceList().size() == 0) {
            return this.getJsonMessage(CommonEnums.SUCCESS, "");
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, model);
    }
}
