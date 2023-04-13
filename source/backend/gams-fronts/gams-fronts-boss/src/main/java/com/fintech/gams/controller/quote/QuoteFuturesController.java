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
import com.fintech.gams.quote.entity.QuoteFutures;
import com.fintech.gams.quote.service.QuoteFuturesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 期货行情 控制器
 * <p>File：QuoteFuturesController.java </p>
 * <p>Title: QuoteFuturesController </p>
 * <p>Description:QuoteFuturesController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.QUOTE)
@Api(description = "期货行情")
public class QuoteFuturesController extends GenericController
{
    @Autowired(required = false)
    private QuoteFuturesService quoteFuturesService;

    @Autowired(required = false)
    private StockInfoService stockInfoService;

    @GetMapping(value = "/futures/findBy")
    @RequiresPermissions("quote:quoteFutures:data")
    @ApiOperation(value = "根据ID取期货行情", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, quoteFuturesService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/futures/save")
    @RequiresPermissions("quote:quoteFutures:operator")
    @ApiOperation(value = "保存期货行情", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute QuoteFutures quoteFutures) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == quoteFutures.getId()) {
            quoteFutures.setCreateBy(principal.getId());
            quoteFutures.setCreateDate(System.currentTimeMillis());
        }
        quoteFutures.setUpdateBy(principal.getId());
        quoteFutures.setUpdateDate(System.currentTimeMillis());
        if (beanValidator(json, quoteFutures))
        {
            quoteFutures.setStockCode(stockInfoService.selectByPrimaryKey(quoteFutures.getStockinfoId()).getStockCode());
            quoteFuturesService.save(quoteFutures);
        }
        return json;
    }

    @PostMapping(value = "/futures/data")
    @RequiresPermissions("quote:quoteFutures:data")
    @ApiOperation(value = "查询期货行情", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute QuoteFutures entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<QuoteFutures> result = quoteFuturesService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/futures/del")
    @RequiresPermissions("quote:quoteFutures:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        quoteFuturesService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
