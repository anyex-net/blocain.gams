/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.account.entity.AccountPerTradeExp;
import com.fintech.gams.account.model.PerTradeExpModel;
import com.fintech.gams.account.service.AccountCommonTradeLocationService;
import com.fintech.gams.account.service.AccountPerTradeExpService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 个人交易经验信息 控制器
 * <p>File：AccountPerTradeExpController.java </p>
 * <p>Title: AccountPerTradeExpController </p>
 * <p>Description:AccountPerTradeExpController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountPerTradeExp")
@Api(description = "个人交易经验信息")
public class AccountPerTradeExpController extends GenericController
{
    @Autowired(required = false)
    private AccountPerTradeExpService accountPerTradeExpService;
    @Autowired(required = false)
    private AccountCommonTradeLocationService accountCommonTradeLocationService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountPerTradeExp:data")
    @ApiOperation(value = "根据ID取个人交易经验信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerTradeExpService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountPerTradeExp:operator")
    @ApiOperation(value = "保存个人交易经验信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountPerTradeExp info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountPerTradeExpService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountPerTradeExp:data")
    @ApiOperation(value = "查询个人交易经验信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountPerTradeExp entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountPerTradeExp> result = accountPerTradeExpService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountPerTradeExp:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountPerTradeExpService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据accountId取个人交易经验信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountPerTradeExp accountPerTradeExp = new AccountPerTradeExp();
        accountPerTradeExp.setAccountId(principal.getId());

        List<AccountPerTradeExp> accountPerTradeExps= accountPerTradeExpService.findList(accountPerTradeExp);
        List<AccountCommonTradeLocation> accountCommonTradeLocations=new ArrayList<>();
        AccountCommonTradeLocation accountCommonTradeLocation =new AccountCommonTradeLocation();
        for(AccountPerTradeExp perTradeExp:accountPerTradeExps){
            accountCommonTradeLocation.setTansactionType(perTradeExp.getTansactionType());
            List<String> locationStrsList = Arrays.asList(perTradeExp.getTradingLocation().split(","));
            for (String locationStr:locationStrsList){
                accountCommonTradeLocation.setTansactionLocation(locationStr);
                accountCommonTradeLocations.addAll(accountCommonTradeLocationService.findList(accountCommonTradeLocation));
            }
        }
        PerTradeExpModel perTradeExpModel = new PerTradeExpModel();
        perTradeExpModel.setAccountPerTradeExpList(accountPerTradeExps);
        perTradeExpModel.setAccountCommonTradeLocations(accountCommonTradeLocations);
        return this.getJsonMessage(CommonEnums.SUCCESS, perTradeExpModel);
    }


}

