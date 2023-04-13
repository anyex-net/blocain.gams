/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.stock;

import java.util.List;

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
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.stock.entity.StockExchange;
import com.fintech.gams.stock.service.StockExchangeService;
import com.fintech.gams.utils.OnLineUserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 证券交易所信息 控制器
 * <p>File：StockExchangeController.java </p>
 * <p>Title: StockExchangeController </p>
 * <p>Description:StockExchangeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.STOCK)
@Api(description = "证券交易所信息")
public class StockExchangeController extends GenericController
{
    @Autowired(required = false)
    private StockExchangeService stockExchangeService;
    
    @GetMapping(value = "/exchange/findBy")
    @RequiresPermissions("stock:stockExchange:data")
    @ApiOperation(value = "根据ID取证券交易所信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, stockExchangeService.selectByPrimaryKey(id));
    }
    
    @GetMapping(value = "/exchange/findAll")
    @RequiresPermissions("stock:stockExchange:data")
    @ApiOperation(value = "获取全部证券交易所信息", httpMethod = "GET")
    public JsonMessage findAll() throws BusinessException
    {
        List<StockExchange> result = stockExchangeService.selectAll();
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @PostMapping(value = "/exchange/save")
    @RequiresPermissions("stock:stockExchange:operator")
    @ApiOperation(value = "保存证券交易所信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute StockExchange info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == info.getId())
        {
            info.setCreateBy(principal.getId());
            info.setCreateDate(System.currentTimeMillis());
        }
        info.setUpdateBy(principal.getId());
        info.setUpdateDate(System.currentTimeMillis());
        if (beanValidator(json, info))
        {
            stockExchangeService.save(info);
        }
        return json;
    }
    
    @PostMapping(value = "/exchange/data")
    @RequiresPermissions("stock:stockExchange:data")
    @ApiOperation(value = "查询证券交易所信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute StockExchange entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<StockExchange> result = stockExchangeService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @PostMapping(value = "/exchange/del")
    @RequiresPermissions("stock:stockExchange:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        stockExchangeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
