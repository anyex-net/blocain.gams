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
import com.fintech.gams.quote.entity.QuoteWarrant;
import com.fintech.gams.quote.service.QuoteWarrantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 权证行情 控制器
 * <p>File：QuoteWarrantController.java </p>
 * <p>Title: QuoteWarrantController </p>
 * <p>Description:QuoteWarrantController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.QUOTE)
@Api(description = "权证行情")
public class QuoteWarrantController extends GenericController
{
    @Autowired(required = false)
    private QuoteWarrantService quoteWarrantService;

    @Autowired(required = false)
    private StockInfoService stockInfoService;

    @GetMapping(value = "/quoteWarrant/findBy")
    @RequiresPermissions("quote:quoteWarrant:data")
    @ApiOperation(value = "根据ID取权证行情", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, quoteWarrantService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/quoteWarrant/save")
    @RequiresPermissions("quote:quoteWarrant:operator")
    @ApiOperation(value = "保存权证行情", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute QuoteWarrant quoteWarrant) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == quoteWarrant.getId()) {
            quoteWarrant.setCreateBy(principal.getId());
            quoteWarrant.setCreateDate(System.currentTimeMillis());
        }
        quoteWarrant.setUpdateBy(principal.getId());
        quoteWarrant.setUpdateDate(System.currentTimeMillis());
        if (beanValidator(json, quoteWarrant))
        {
            quoteWarrant.setStockCode(stockInfoService.selectByPrimaryKey(quoteWarrant.getStockinfoId()).getStockCode());
            quoteWarrantService.save(quoteWarrant);
        }
        return json;
    }

    @PostMapping(value = "/quoteWarrant/data")
    @RequiresPermissions("quote:quoteWarrant:data")
    @ApiOperation(value = "查询权证行情", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute QuoteWarrant entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<QuoteWarrant> result = quoteWarrantService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/quoteWarrant/del")
    @RequiresPermissions("quote:quoteWarrant:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        quoteWarrantService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
