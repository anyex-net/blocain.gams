/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.stock;

import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.StringUtils;
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
import com.fintech.gams.stock.entity.StockInfo;
import com.fintech.gams.stock.service.StockInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 证券信息 控制器
 * <p>File：StockInfoController.java </p>
 * <p>Title: StockInfoController </p>
 * <p>Description:StockInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.STOCK)
@Api(description = "证券信息")
public class StockInfoController extends GenericController
{
    @Autowired(required = false)
    private StockInfoService stockInfoService;
    
    @GetMapping(value = "/info/findBy")
    @RequiresPermissions("stock:stockInfo:data")
    @ApiOperation(value = "根据ID取证券信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, stockInfoService.selectByPrimaryKey(id));
    }

    @GetMapping(value = "/info/findAll")
    @RequiresPermissions("stock:stockInfo:data")
    @ApiOperation(value = "获取全部证券信息", httpMethod = "GET")
    public JsonMessage findAll() throws BusinessException
    {
        List<StockInfo> result = stockInfoService.selectAll();
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/info/findListByStockType")
    @RequiresPermissions("stock:stockInfo:data")
    @ApiOperation(value = "获取指定证券类型的证券信息", httpMethod = "GET")
    public JsonMessage findListByStockType(String stockType) throws BusinessException
    {
        StockInfo stockInfo = new StockInfo();
        if(StringUtils.isNotEmpty(stockType))
        {
            stockInfo.setStockType(stockType);
        }
        List<StockInfo> result = stockInfoService.findList(stockInfo);
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/info/save")
    @RequiresPermissions("stock:stockInfo:operator")
    @ApiOperation(value = "保存证券信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute StockInfo info) throws BusinessException
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
            stockInfoService.save(info);
        }
        return json;
    }
    
    @PostMapping(value = "/info/data")
    @RequiresPermissions("stock:stockInfo:data")
    @ApiOperation(value = "查询证券信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute StockInfo entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<StockInfo> result = stockInfoService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @PostMapping(value = "/info/del")
    @RequiresPermissions("stock:stockInfo:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        stockInfoService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
