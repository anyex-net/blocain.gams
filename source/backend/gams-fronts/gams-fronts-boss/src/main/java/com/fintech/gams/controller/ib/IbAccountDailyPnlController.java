/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.ib;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.ib.entity.IbAccountDailyPnl;
import com.fintech.gams.ib.service.IbAccountDailyPnlService;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.CalendarUtils;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * IB账户当日盈亏 控制器
 * <p>File：IbAccountDailyPnlController.java </p>
 * <p>Title: IbAccountDailyPnlController </p>
 * <p>Description:IbAccountDailyPnlController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/ib/ibAccountDailyPnl")
@Api(description = "IB账户当日盈亏")
public class IbAccountDailyPnlController extends GenericController
{
    @Autowired(required = false)
    private IbAccountDailyPnlService ibAccountDailyPnlService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("ib:ibAccountDailyPnl:data")
    @ApiOperation(value = "根据ID取IB账户当日盈亏", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, ibAccountDailyPnlService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("ib:ibAccountDailyPnl:operator")
    @ApiOperation(value = "保存IB账户当日盈亏", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute IbAccountDailyPnl info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        long currentDate = CalendarUtils.getCurrentLong();
        if (null == info.getId())
        {
            info.setCreateDate(currentDate);
        }
        info.setUpdateDate(currentDate);
        ibAccountDailyPnlService.save(info);
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("ib:ibAccountDailyPnl:data")
    @ApiOperation(value = "查询IB账户当日盈亏", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute IbAccountDailyPnl entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<IbAccountDailyPnl> result = ibAccountDailyPnlService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("ib:ibAccountDailyPnl:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        ibAccountDailyPnlService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
