/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.stock;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.stock.entity.StockWarrant;
import com.fintech.gams.stock.service.StockWarrantService;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/stockWarrant/findWarrant")
    @ApiOperation(value = "查询证券权证信息", httpMethod = "POST")
    public JsonMessage findWarrant(@ModelAttribute StockWarrant entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<StockWarrant> result = stockWarrantService.searchStockWarrant(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
