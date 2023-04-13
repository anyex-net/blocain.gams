/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.asset;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.asset.entity.AssetCurrent;
import com.fintech.gams.asset.service.AssetCurrentService;
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
 * 资产流水表 控制器
 * <p>File：AssetCurrentController.java </p>
 * <p>Title: AssetCurrentController </p>
 * <p>Description:AssetCurrentController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.ASSET)
@Api(description = "资产流水表")
public class AssetCurrentController extends GenericController
{
    @Autowired(required = false)
    private AssetCurrentService assetCurrentService;
    
    @GetMapping(value = "/current/findBy")
    @RequiresPermissions("asset:assetCurrent:data")
    @ApiOperation(value = "根据ID取资产流水表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, assetCurrentService.selectByPrimaryKey(id));
    }
    
    @PostMapping(value = "/current/save")
    @RequiresPermissions("asset:assetCurrent:operator")
    @ApiOperation(value = "保存资产流水表", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AssetCurrent info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            assetCurrentService.save(info);
        }
        return json;
    }
    
    @PostMapping(value = "/current/data")
    @RequiresPermissions("asset:assetCurrent:data")
    @ApiOperation(value = "查询资产流水表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AssetCurrent entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AssetCurrent> result = assetCurrentService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @PostMapping(value = "/current/del")
    @RequiresPermissions("asset:assetCurrent:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        assetCurrentService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
