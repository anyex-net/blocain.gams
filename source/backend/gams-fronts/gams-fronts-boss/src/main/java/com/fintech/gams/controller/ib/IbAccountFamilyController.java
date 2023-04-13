/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.ib;

import com.fintech.gams.utils.CalendarUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.ib.entity.IbAccountFamily;
import com.fintech.gams.ib.service.IbAccountFamilyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * IB账户家族表 控制器
 * <p>File：IbAccountFamilyController.java </p>
 * <p>Title: IbAccountFamilyController </p>
 * <p>Description:IbAccountFamilyController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/ib/ibAccountFamily")
@Api(description = "IB账户家族表")
public class IbAccountFamilyController extends GenericController
{
    @Autowired(required = false)
    private IbAccountFamilyService ibAccountFamilyService;

    @PostMapping(value = "/data")
    @RequiresPermissions("ib:ibAccountFamily:data")
    @ApiOperation(value = "查询IB账户家族表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute IbAccountFamily entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<IbAccountFamily> result = ibAccountFamilyService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @RequiresPermissions("ib:ibAccountFamily:data")
    @ApiOperation(value = "根据ID取IB账户家族表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, ibAccountFamilyService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("ib:ibAccountFamily:operator")
    @ApiOperation(value = "保存IB账户家族表", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute IbAccountFamily info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        long currentDate = CalendarUtils.getCurrentLong();
        if (null == info.getId())
        {
            info.setCreateDate(currentDate);
        }
        info.setUpdateDate(currentDate);
        ibAccountFamilyService.save(info);
        return json;
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("ib:ibAccountFamily:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        ibAccountFamilyService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
