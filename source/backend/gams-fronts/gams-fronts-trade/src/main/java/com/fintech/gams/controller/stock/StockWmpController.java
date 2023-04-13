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
import com.fintech.gams.stock.entity.StockWmp;
import com.fintech.gams.stock.service.StockWmpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 证券理财产品信息 控制器
 * <p>File：StockWmpController.java </p>
 * <p>Title: StockWmpController </p>
 * <p>Description:StockWmpController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.STOCK)
@Api(description = "证券理财产品信息")
public class StockWmpController extends GenericController {
    @Autowired(required = false)
    private StockWmpService stockWmpService;

    @PostMapping(value = "/stockWmp/findWmp")
    @ApiOperation(value = "查询证券理财产品信息", httpMethod = "POST")
    public JsonMessage findWmp(@ModelAttribute StockWmp entity, @ModelAttribute Pagination pagin) throws BusinessException {
        PaginateResult<StockWmp> result = stockWmpService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/stockWmp/findRecommend")
    @ApiOperation(value = "证券理财推荐产品信息", httpMethod = "POST")
    public JsonMessage findRecommend() throws BusinessException {
        StockWmp stockWmp = new StockWmp();
        stockWmp.setIsRecommend(true);
        List<StockWmp> result = stockWmpService.findList(stockWmp);
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
