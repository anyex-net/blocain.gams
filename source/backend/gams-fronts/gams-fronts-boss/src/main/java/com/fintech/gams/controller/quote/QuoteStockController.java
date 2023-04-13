/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.quote;

import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.stock.service.StockInfoService;
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
import com.fintech.gams.quote.entity.QuoteStock;
import com.fintech.gams.quote.service.QuoteStockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 股票行情 控制器
 * <p>File：QuoteStockController.java </p>
 * <p>Title: QuoteStockController </p>
 * <p>Description:QuoteStockController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.QUOTE)
@Api(description = "股票行情")
public class QuoteStockController extends GenericController
{
    @Autowired(required = false)
    private QuoteStockService quoteStockService;

    @Autowired(required = false)
    private StockInfoService stockInfoService;

    @GetMapping(value = "/stock/findBy")
    @RequiresPermissions("quote:quoteStock:data")
    @ApiOperation(value = "根据ID取股票行情", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, quoteStockService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/stock/save")
    @RequiresPermissions("quote:quoteStock:operator")
    @ApiOperation(value = "保存股票行情", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute QuoteStock quoteStock) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == quoteStock.getId()) {
            quoteStock.setCreateBy(principal.getId());
            quoteStock.setCreateDate(System.currentTimeMillis());
        }
        quoteStock.setUpdateBy(principal.getId());
        quoteStock.setUpdateDate(System.currentTimeMillis());
        if (beanValidator(json, quoteStock))
        {
            if(stockInfoService.selectByPrimaryKey(quoteStock.getStockinfoId()) != null)
                quoteStock.setStockCode(stockInfoService.selectByPrimaryKey(quoteStock.getStockinfoId()).getStockCode());
            quoteStockService.save(quoteStock);
        }
        return json;
    }

    @PostMapping(value = "/stock/data")
    @RequiresPermissions("quote:quoteStock:data")
    @ApiOperation(value = "查询股票行情", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute QuoteStock entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<QuoteStock> result = quoteStockService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/stock/del")
    @RequiresPermissions("quote:quoteStock:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        quoteStockService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
