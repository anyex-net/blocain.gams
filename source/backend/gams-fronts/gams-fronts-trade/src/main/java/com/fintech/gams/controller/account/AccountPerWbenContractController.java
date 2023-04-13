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
import com.fintech.gams.account.entity.AccountPerWbenContract;
import com.fintech.gams.account.service.AccountPerWbenContractService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 个人WBen合约 控制器
 * <p>File：AccountPerWbenContractController.java </p>
 * <p>Title: AccountPerWbenContractController </p>
 * <p>Description:AccountPerWbenContractController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountPerWbenContract")
@Api(description = "个人WBen合约")
public class AccountPerWbenContractController extends GenericController
{
    @Autowired(required = false)
    private AccountPerWbenContractService accountPerWbenContractService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountPerWbenContract:data")
    @ApiOperation(value = "根据ID取个人WBen合约", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerWbenContractService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountPerWbenContract:operator")
    @ApiOperation(value = "保存个人WBen合约", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountPerWbenContract info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountPerWbenContractService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountPerWbenContract:data")
    @ApiOperation(value = "查询个人WBen合约", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountPerWbenContract entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountPerWbenContract> result = accountPerWbenContractService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountPerWbenContract:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountPerWbenContractService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据accountId取个人WBen合约", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountPerWbenContract accountPerWbenContract=new AccountPerWbenContract();
        accountPerWbenContract.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerWbenContractService.findList(accountPerWbenContract));
    }

}
