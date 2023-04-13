/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.ib;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.ib.entity.IbAccountTrade;
import com.fintech.gams.ib.service.IbAccountTradeService;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(GlobalConst.IB)
@Api(description = "IB账户交易流水表")
public class IbAccountTradeController extends GenericController
{
    @Autowired(required = false)
    private IbAccountTradeService ibAccountTradeService;

    @PostMapping(value = "/accountTrade/list")
    @ApiOperation(value = "IB账户交易流水记录查询", httpMethod = "POST")
    public JsonMessage list(@ModelAttribute IbAccountTrade ibAccountTrade, @ModelAttribute Pagination pagin) throws BusinessException
    {
        ibAccountTrade.setAccountId(OnLineUserUtils.getId());
        // ibAccountTrade.setAccountCode("U8138565");
        PaginateResult<IbAccountTrade> result = ibAccountTradeService.search(pagin, ibAccountTrade);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
