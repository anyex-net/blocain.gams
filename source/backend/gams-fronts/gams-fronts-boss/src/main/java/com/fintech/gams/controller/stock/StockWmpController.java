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
import com.fintech.gams.stock.entity.StockWmp;
import com.fintech.gams.stock.service.StockWmpService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 证券理财产品信息 控制器
 * <p>File：StockWmpController.java </p>
 * <p>Title: StockWmpController </p>
 * <p>Description:StockWmpController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.STOCK)
@Api(description = "证券理财产品信息")
public class StockWmpController extends GenericController
{
    @Autowired(required = false)
    private StockWmpService stockWmpService;

    @GetMapping(value = "/stockWmp/findBy")
    @RequiresPermissions("stock:stockWmp:data")
    @ApiOperation(value = "根据ID取证券理财产品信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, stockWmpService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/stockWmp/save")
    @RequiresPermissions("stock:stockWmp:operator")
    @ApiOperation(value = "保存证券理财产品信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute StockWmp info) throws BusinessException
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
            stockWmpService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/stockWmp/data")
    @RequiresPermissions("stock:stockWmp:data")
    @ApiOperation(value = "查询证券理财产品信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute StockWmp entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<StockWmp> result = stockWmpService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/stockWmp/del")
    @RequiresPermissions("stock:stockWmp:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        stockWmpService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
