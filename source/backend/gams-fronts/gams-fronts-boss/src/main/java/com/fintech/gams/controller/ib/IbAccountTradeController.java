/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.ib;

import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.CalendarUtils;
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
import com.fintech.gams.ib.entity.IbAccountTrade;
import com.fintech.gams.ib.service.IbAccountTradeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * IB账户交易流水表 控制器
 * <p>File：IbAccountTradeController.java </p>
 * <p>Title: IbAccountTradeController </p>
 * <p>Description:IbAccountTradeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/ib/ibAccountTrade")
@Api(description = "IB账户交易流水表")
public class IbAccountTradeController extends GenericController
{
    @Autowired(required = false)
    private IbAccountTradeService ibAccountTradeService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("ib:ibAccountTrade:data")
    @ApiOperation(value = "根据ID取IB账户交易流水表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, ibAccountTradeService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("ib:ibAccountTrade:operator")
    @ApiOperation(value = "保存IB账户交易流水表", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute IbAccountTrade info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        long currentDate = CalendarUtils.getCurrentLong();
        if (null == info.getId())
        {
            info.setCreateDate(currentDate);
        }
        info.setUpdateDate(currentDate);
        ibAccountTradeService.save(info);
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("ib:ibAccountTrade:data")
    @ApiOperation(value = "查询IB账户交易流水表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute IbAccountTrade entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<IbAccountTrade> result = ibAccountTradeService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("ib:ibAccountTrade:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        ibAccountTradeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
