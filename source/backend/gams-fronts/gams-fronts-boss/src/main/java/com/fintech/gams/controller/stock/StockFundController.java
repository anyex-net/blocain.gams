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
import com.fintech.gams.stock.entity.StockFund;
import com.fintech.gams.stock.service.StockFundService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 证券基金信息 控制器
 * <p>File：StockFundController.java </p>
 * <p>Title: StockFundController </p>
 * <p>Description:StockFundController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.STOCK)
@Api(description = "证券基金信息")
public class StockFundController extends GenericController
{
    @Autowired(required = false)
    private StockFundService stockFundService;

    @GetMapping(value = "/fund/findBy")
    @RequiresPermissions("stock:stockFund:data")
    @ApiOperation(value = "根据ID取证券基金信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, stockFundService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/fund/save")
    @RequiresPermissions("stock:stockFund:operator")
    @ApiOperation(value = "保存证券基金信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute StockFund info) throws BusinessException
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
            stockFundService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/fund/data")
    @RequiresPermissions("stock:stockFund:data")
    @ApiOperation(value = "查询证券基金信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute StockFund entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<StockFund> result = stockFundService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/fund/del")
    @RequiresPermissions("stock:stockFund:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        stockFundService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
