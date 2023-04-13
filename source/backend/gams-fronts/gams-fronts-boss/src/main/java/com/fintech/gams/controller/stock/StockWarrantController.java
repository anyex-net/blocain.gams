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
import com.fintech.gams.stock.entity.StockWarrant;
import com.fintech.gams.stock.service.StockWarrantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 证券权证信息 控制器
 * <p>File：StockWarrantController.java </p>
 * <p>Title: StockWarrantController </p>
 * <p>Description:StockWarrantController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.STOCK)
@Api(description = "证券权证信息")
public class StockWarrantController extends GenericController
{
    @Autowired(required = false)
    private StockWarrantService stockWarrantService;

    @GetMapping(value = "/stockWarrant/findBy")
    @RequiresPermissions("stock:stockWarrant:data")
    @ApiOperation(value = "根据ID取证券权证信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, stockWarrantService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/stockWarrant/save")
    @RequiresPermissions("stock:stockWarrant:operator")
    @ApiOperation(value = "保存证券权证信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute StockWarrant info) throws BusinessException
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
            stockWarrantService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/stockWarrant/data")
    @RequiresPermissions("stock:stockWarrant:data")
    @ApiOperation(value = "查询证券权证信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute StockWarrant entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<StockWarrant> result = stockWarrantService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/stockWarrant/del")
    @RequiresPermissions("stock:stockWarrant:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        stockWarrantService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
