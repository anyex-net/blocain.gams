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
import com.fintech.gams.ib.entity.IbAccountFamily;
import com.fintech.gams.ib.entity.IbAccountMarketValue;
import com.fintech.gams.ib.service.IbAccountFamilyService;
import com.fintech.gams.ib.service.IbAccountMarketValueService;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * IB账户市值表 控制器
 * <p>File：IbAccountMarketValueController.java </p>
 * <p>Title: IbAccountMarketValueController </p>
 * <p>Description:IbAccountMarketValueController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.IB)
@Api(description = "IB账户市值表")
public class IbAccountMarketValueController extends GenericController
{
    @Autowired(required = false)
    private IbAccountFamilyService ibAccountFamilyService;

    @Autowired(required = false)
    private IbAccountMarketValueService ibAccountMarketValueService;

    @PostMapping(value = "/accountMarketValue/accountMarketValue")
    @ApiOperation(value = "IB账户市值", httpMethod = "POST")
    public JsonMessage accountMarketValue() throws BusinessException
    {
        IbAccountMarketValue ibAccountMarketValue = new IbAccountMarketValue();
        ibAccountMarketValue.setAccountId(OnLineUserUtils.getId());
        ibAccountMarketValue.setCurrency("BASE");
        // ibAccountMarketValue.setAccountCode("U8138565");
        List<IbAccountMarketValue> listIbAccountMarketValue = ibAccountMarketValueService.findList(ibAccountMarketValue);
        IbAccountMarketValue accountMarketValue = null;
        if(null != listIbAccountMarketValue && listIbAccountMarketValue.size() > 0)
        {
            accountMarketValue = listIbAccountMarketValue.get(0);
            // 处理本位币
            IbAccountFamily ibAccountFamily = new IbAccountFamily();
            ibAccountFamily.setAccountId(OnLineUserUtils.getId());
            // ibAccountFamily.setIbAccountCode("U8138565");
            List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamily);
            accountMarketValue.setCurrency(listIbAccountFamily.get(0).getBaseCurrency());
        }

        return getJsonMessage(CommonEnums.SUCCESS, accountMarketValue);
    }

    @PostMapping(value = "/accountMarketValue/findExchangeRateList")
    @ApiOperation(value = "IB账户市值汇率行情查询", httpMethod = "POST")
    public JsonMessage findExchangeRateList() throws BusinessException
    {
        IbAccountMarketValue ibAccountMarketValue = new IbAccountMarketValue();
        ibAccountMarketValue.setAccountId(OnLineUserUtils.getId());
        // ibAccountMarketValue.setAccountCode("U8138565");
        List<IbAccountMarketValue> result = ibAccountMarketValueService.findExchangeRateList(ibAccountMarketValue.getAccountId());
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
