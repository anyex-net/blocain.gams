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
import com.fintech.gams.quote.entity.QuoteForeignEx;
import com.fintech.gams.quote.service.QuoteForeignExService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 外汇行情 控制器
 * <p>File：QuoteForeignExController.java </p>
 * <p>Title: QuoteForeignExController </p>
 * <p>Description:QuoteForeignExController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.QUOTE)
@Api(description = "外汇行情")
public class QuoteForeignExController extends GenericController
{
    @Autowired(required = false)
    private QuoteForeignExService quoteForeignexService;

    @Autowired(required = false)
    private StockInfoService stockInfoService;

    @GetMapping(value = "/foreignex/findBy")
    @RequiresPermissions("quote:quoteForeignEx:data")
    @ApiOperation(value = "根据ID取外汇行情", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, quoteForeignexService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/foreignex/save")
    @RequiresPermissions("quote:quoteForeignEx:operator")
    @ApiOperation(value = "保存外汇行情", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute QuoteForeignEx quoteForeignex) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == quoteForeignex.getId()) {
            quoteForeignex.setCreateBy(principal.getId());
            quoteForeignex.setCreateDate(System.currentTimeMillis());
        }
        quoteForeignex.setUpdateBy(principal.getId());
        quoteForeignex.setUpdateDate(System.currentTimeMillis());
        if (beanValidator(json, quoteForeignex))
        {
            quoteForeignex.setStockCode(stockInfoService.selectByPrimaryKey(quoteForeignex.getStockinfoId()).getStockCode());
            quoteForeignexService.save(quoteForeignex);
        }
        return json;
    }

    @PostMapping(value = "/foreignex/data")
    @RequiresPermissions("quote:quoteForeignEx:data")
    @ApiOperation(value = "查询外汇行情", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute QuoteForeignEx entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<QuoteForeignEx> result = quoteForeignexService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/foreignex/del")
    @RequiresPermissions("quote:quoteForeignEx:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        quoteForeignexService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
