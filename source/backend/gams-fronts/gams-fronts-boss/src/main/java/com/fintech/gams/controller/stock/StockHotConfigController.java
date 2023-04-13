/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.stock;

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
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.stock.entity.StockHotConfig;
import com.fintech.gams.stock.service.StockHotConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 证券热门推荐配置 控制器
 * <p>File：StockHotConfigController.java </p>
 * <p>Title: StockHotConfigController </p>
 * <p>Description:StockHotConfigController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.STOCK)
@Api(description = "证券热门推荐配置")
public class StockHotConfigController extends GenericController
{
    @Autowired(required = false)
    private StockHotConfigService stockHotConfigService;

    @GetMapping(value = "/hotConfig/findBy")
    @RequiresPermissions("stock:stockHotConfig:data")
    @ApiOperation(value = "根据ID取证券热门推荐配置", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, stockHotConfigService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/hotConfig/save")
    @RequiresPermissions("stock:stockHotConfig:operator")
    @ApiOperation(value = "保存证券热门推荐配置", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute StockHotConfig info) throws BusinessException
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
            stockHotConfigService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/hotConfig/data")
    @RequiresPermissions("stock:stockHotConfig:data")
    @ApiOperation(value = "查询证券热门推荐配置", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute StockHotConfig entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<StockHotConfig> result = stockHotConfigService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/hotConfig/del")
    @RequiresPermissions("stock:stockHotConfig:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        stockHotConfigService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
