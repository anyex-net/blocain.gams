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
import com.fintech.gams.quote.entity.QuoteFund;
import com.fintech.gams.quote.service.QuoteFundService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 基金行情 控制器
 * <p>File：QuoteFundController.java </p>
 * <p>Title: QuoteFundController </p>
 * <p>Description:QuoteFundController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.QUOTE)
@Api(description = "基金行情")
public class QuoteFundController extends GenericController
{
    @Autowired(required = false)
    private QuoteFundService quoteFundService;

    @Autowired(required = false)
    private StockInfoService stockInfoService;

    @GetMapping(value = "/fund/findBy")
    @RequiresPermissions("quote:quoteFund:data")
    @ApiOperation(value = "根据ID取基金行情", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, quoteFundService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/fund/save")
    @RequiresPermissions("quote:quoteFund:operator")
    @ApiOperation(value = "保存基金行情", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute QuoteFund quoteFund) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == quoteFund.getId()) {
            quoteFund.setCreateBy(principal.getId());
            quoteFund.setCreateDate(System.currentTimeMillis());
        }
        quoteFund.setUpdateBy(principal.getId());
        quoteFund.setUpdateDate(System.currentTimeMillis());
        if (beanValidator(json, quoteFund))
        {
            quoteFund.setStockCode(stockInfoService.selectByPrimaryKey(quoteFund.getStockinfoId()).getStockCode());
            quoteFundService.save(quoteFund);
        }
        return json;
    }

    @PostMapping(value = "/fund/data")
    @RequiresPermissions("quote:quoteFund:data")
    @ApiOperation(value = "查询基金行情", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute QuoteFund entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<QuoteFund> result = quoteFundService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/fund/del")
    @RequiresPermissions("quote:quoteFund:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        quoteFundService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
