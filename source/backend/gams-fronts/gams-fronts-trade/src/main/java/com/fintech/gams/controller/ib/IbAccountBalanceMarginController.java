/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.ib;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.ib.entity.IbAccountBalanceMargin;
import com.fintech.gams.ib.service.IbAccountBalanceMarginService;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * IB账户资金保证金表 控制器
 * <p>File：IbAccountBalanceMarginController.java </p>
 * <p>Title: IbAccountBalanceMarginController </p>
 * <p>Description:IbAccountBalanceMarginController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.IB)
@Api(description = "IB账户资金保证金表")
public class IbAccountBalanceMarginController extends GenericController
{
    @Autowired(required = false)
    private IbAccountBalanceMarginService ibAccountBalanceMarginService;

    @PostMapping(value = "/accountBalanceMargin/cashSummary")
    @ApiOperation(value = "IB账户资金概览", httpMethod = "POST")
    public JsonMessage cashSummary() throws BusinessException
    {
        IbAccountBalanceMargin ibAccountBalanceMargin = new IbAccountBalanceMargin();
        ibAccountBalanceMargin.setAccountId(OnLineUserUtils.getId());
        // ibAccountBalanceMargin.setAccountCode("U8138565");
        List<IbAccountBalanceMargin> listIbAccountBalanceMargin = ibAccountBalanceMarginService.findList(ibAccountBalanceMargin);
        IbAccountBalanceMargin cashSummary = null;
        if(null != listIbAccountBalanceMargin && listIbAccountBalanceMargin.size() > 0)
        {
            cashSummary = listIbAccountBalanceMargin.get(0);
        }
        return getJsonMessage(CommonEnums.SUCCESS, cashSummary);
    }

}
