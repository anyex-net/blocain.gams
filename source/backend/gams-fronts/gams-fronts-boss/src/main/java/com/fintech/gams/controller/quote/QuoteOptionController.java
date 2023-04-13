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
import com.fintech.gams.quote.entity.QuoteOption;
import com.fintech.gams.quote.service.QuoteOptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 期权行情 控制器
 * <p>File：QuoteOptionController.java </p>
 * <p>Title: QuoteOptionController </p>
 * <p>Description:QuoteOptionController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.QUOTE)
@Api(description = "期权行情")
public class QuoteOptionController extends GenericController
{
    @Autowired(required = false)
    private QuoteOptionService quoteOptionService;

    @Autowired(required = false)
    private StockInfoService stockInfoService;

    @GetMapping(value = "/option/findBy")
    @RequiresPermissions("quote:quoteOption:data")
    @ApiOperation(value = "根据ID取期权行情", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, quoteOptionService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/option/save")
    @RequiresPermissions("quote:quoteOption:operator")
    @ApiOperation(value = "保存期权行情", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute QuoteOption quoteOption) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == quoteOption.getId()) {
            quoteOption.setCreateBy(principal.getId());
            quoteOption.setCreateDate(System.currentTimeMillis());
        }
        quoteOption.setUpdateBy(principal.getId());
        quoteOption.setUpdateDate(System.currentTimeMillis());
        if (beanValidator(json, quoteOption))
        {
            quoteOption.setStockCode(stockInfoService.selectByPrimaryKey(quoteOption.getStockinfoId()).getStockCode());
            quoteOptionService.save(quoteOption);
        }
        return json;
    }

    @PostMapping(value = "/option/data")
    @RequiresPermissions("quote:quoteOption:data")
    @ApiOperation(value = "查询期权行情", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute QuoteOption entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<QuoteOption> result = quoteOptionService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/option/del")
    @RequiresPermissions("quote:quoteOption:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        quoteOptionService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
