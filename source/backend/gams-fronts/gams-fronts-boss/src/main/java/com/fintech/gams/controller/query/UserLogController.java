/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.query;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.system.entity.UserLog;
import com.fintech.gams.system.service.UserLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 用户日志 控制器
 * <p>File：UserLogController.java </p>
 * <p>Title: UserLogController </p>
 * <p>Description:UserLogController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.QUERY)
@Api(description = "用户日志")
public class UserLogController extends GenericController
{
    @Autowired(required = false)
    private UserLogService userLogService;
    
    @GetMapping(value = "/userLog/findBy")
    @RequiresPermissions("query:userLog:data")
    @ApiOperation(value = "根据ID取用户日志", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, userLogService.selectByPrimaryKey(id));
    }
    
    @PostMapping(value = "/userLog/data")
    @RequiresPermissions("query:userLog:data")
    @ApiOperation(value = "查询用户日志", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute UserLog entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<UserLog> result = userLogService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @PostMapping(value = "/userLog/del")
    @RequiresPermissions("query:userLog:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        userLogService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
