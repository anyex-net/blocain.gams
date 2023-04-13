/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 区域代码 控制器
 * <p>File：RegionController.java </p>
 * <p>Title: RegionController </p>
 * <p>Description:RegionController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(description = "区域代码")
public class RegionController extends GenericController
{
    @Autowired(required = false)
    private RegionService regionService;
    
    @GetMapping(value = "/region/findBy")
    //@RequiresPermissions("common:region:data")
    @ApiOperation(value = "根据ID取区域代码", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, regionService.selectByPrimaryKey(id));
    }
    
    @GetMapping(value = "/region/findAll")
    //@RequiresPermissions("common:region:data")
    @ApiOperation(value = "查全部数据", httpMethod = "GET")
    public JsonMessage findAll() throws BusinessException
    {
        return this.getJsonMessage(CommonEnums.SUCCESS, regionService.selectAll());
    }

    @PostMapping(value = "/region/data")
    //@RequiresPermissions("common:region:data")
    @ApiOperation(value = "查询区域代码", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute Region entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<Region> result = regionService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @PostMapping(value = "/region/save")
    @RequiresPermissions("common:region:operator")
    @ApiOperation(value = "保存区域代码", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute Region info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            regionService.save(info);
        }
        return json;
    }
    
    @PostMapping(value = "/region/del")
    @RequiresPermissions("common:region:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        regionService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
