/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.Parameter;
import com.fintech.gams.common.service.ParameterService;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 参数表 控制器
 * <p>File：ParameterController.java </p>
 * <p>Title: ParameterController </p>
 * <p>Description:ParameterController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(description = "参数配置")
public class ParameterController extends GenericController
{
    @Autowired(required = false)
    private ParameterService parameterService;

    @GetMapping(value = "/parameter/findBy")
    @RequiresPermissions("common:parameter:data")
    @ApiOperation(value = "根据ID取参数表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, parameterService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/parameter/save")
    @RequiresPermissions("common:parameter:operator")
    @ApiOperation(value = "保存参数表", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute Parameter info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == info.getId()) {
            info.setCreateBy(principal.getId());
            info.setCreateDate(System.currentTimeMillis());
        }
        info.setUpdateBy(principal.getId());
        info.setUpdateDate(System.currentTimeMillis());
        if (beanValidator(json, info))
        {
            parameterService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/parameter/data")
    @RequiresPermissions("common:parameter:data")
    @ApiOperation(value = "查询参数表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute Parameter entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<Parameter> result = parameterService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/parameter/del")
    @RequiresPermissions("common:parameter:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        parameterService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
