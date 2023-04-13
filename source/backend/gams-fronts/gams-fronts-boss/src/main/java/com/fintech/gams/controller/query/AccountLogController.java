/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.query;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.account.entity.AccountLog;
import com.fintech.gams.account.service.AccountLogService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 帐户日志 控制器
 * <p>File：AccountLogController.java </p>
 * <p>Title: AccountLogController </p>
 * <p>Description:AccountLogController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.QUERY)
@Api(description = "帐户日志")
public class AccountLogController extends GenericController
{
    @Autowired(required = false)
    private AccountLogService accountLogService;
    
    @GetMapping(value = "/accountLog/findBy")
    @RequiresPermissions("query:accountLog:data")
    @ApiOperation(value = "根据ID取帐户日志", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountLogService.selectByPrimaryKey(id));
    }
    
    @PostMapping(value = "/accountLog/data")
    @RequiresPermissions("query:accountLog:data")
    @ApiOperation(value = "查询帐户日志", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountLog entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountLog> result = accountLogService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @PostMapping(value = "/accountLog/del")
    @RequiresPermissions("query:accountLog:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountLogService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
