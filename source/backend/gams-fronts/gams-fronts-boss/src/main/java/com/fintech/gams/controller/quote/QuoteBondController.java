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
import com.fintech.gams.quote.entity.QuoteBond;
import com.fintech.gams.quote.service.QuoteBondService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 债券行情 控制器
 * <p>File：QuoteBondController.java </p>
 * <p>Title: QuoteBondController </p>
 * <p>Description:QuoteBondController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.QUOTE)
@Api(description = "债券行情")
public class QuoteBondController extends GenericController
{
    @Autowired(required = false)
    private QuoteBondService quoteBondService;

    @Autowired(required = false)
    private StockInfoService stockInfoService;

    @GetMapping(value = "/bond/findBy")
    @RequiresPermissions("quote:quoteBond:data")
    @ApiOperation(value = "根据ID取债券行情", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, quoteBondService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/bond/save")
    @RequiresPermissions("quote:quoteBond:operator")
    @ApiOperation(value = "保存债券行情", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute QuoteBond quoteBond) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == quoteBond.getId()) {
            quoteBond.setCreateBy(principal.getId());
            quoteBond.setCreateDate(System.currentTimeMillis());
        }
        quoteBond.setUpdateBy(principal.getId());
        quoteBond.setUpdateDate(System.currentTimeMillis());
        if (beanValidator(json, quoteBond))
        {
            quoteBond.setStockCode(stockInfoService.selectByPrimaryKey(quoteBond.getStockinfoId()).getStockCode());
            quoteBondService.save(quoteBond);
        }
        return json;
    }

    @PostMapping(value = "/bond/data")
    @RequiresPermissions("quote:quoteBond:data")
    @ApiOperation(value = "查询债券行情", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute QuoteBond entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<QuoteBond> result = quoteBondService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/bond/del")
    @RequiresPermissions("quote:quoteBond:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        quoteBondService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
