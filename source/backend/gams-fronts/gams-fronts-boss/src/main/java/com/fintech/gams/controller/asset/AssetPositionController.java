/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.asset;

import com.fintech.gams.consts.GlobalConst;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.asset.entity.AssetPosition;
import com.fintech.gams.asset.service.AssetPositionService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 资产持仓表 控制器
 * <p>File：AssetPositionController.java </p>
 * <p>Title: AssetPositionController </p>
 * <p>Description:AssetPositionController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.ASSET)
@Api(description = "资产持仓表")
public class AssetPositionController extends GenericController
{
    @Autowired(required = false)
    private AssetPositionService assetPositionService;

    @GetMapping(value = "/position/findBy")
    @RequiresPermissions("asset:assetPosition:data")
    @ApiOperation(value = "根据ID取资产持仓表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, assetPositionService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/position/save")
    @RequiresPermissions("asset:assetPosition:operator")
    @ApiOperation(value = "保存资产持仓表", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AssetPosition info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            assetPositionService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/position/data")
    @RequiresPermissions("asset:assetPosition:data")
    @ApiOperation(value = "查询资产持仓表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AssetPosition entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AssetPosition> result = assetPositionService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/position/del")
    @RequiresPermissions("asset:assetPosition:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        assetPositionService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
