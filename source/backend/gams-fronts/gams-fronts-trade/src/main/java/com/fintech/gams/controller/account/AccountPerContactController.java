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
import com.fintech.gams.account.entity.AccountPerContact;
import com.fintech.gams.account.service.AccountPerContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * ACCOUNT_PER_CONTACT 控制器
 * <p>File：AccountPerContactController.java </p>
 * <p>Title: AccountPerContactController </p>
 * <p>Description:AccountPerContactController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountPerContact")
@Api(description = "个人联系方式")
public class AccountPerContactController extends GenericController
{
    @Autowired(required = false)
    private AccountPerContactService accountPerContactService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountPerContact:data")
    @ApiOperation(value = "根据ID取个人联系方式", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerContactService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountPerContact:operator")
    @ApiOperation(value = "保存个人联系方式", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountPerContact info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountPerContactService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountPerContact:data")
    @ApiOperation(value = "查询个人联系方式", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountPerContact entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountPerContact> result = accountPerContactService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountPerContact:operator")
    @ApiOperation(value = "根据指定ID删除个人联系方式", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountPerContactService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }


    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据accountId取个人联系方式", httpMethod = "GET")
    public JsonMessage findByAccount() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountPerContact accountPerContact = new AccountPerContact();
        accountPerContact.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerContactService.findList(accountPerContact));
    }

}
