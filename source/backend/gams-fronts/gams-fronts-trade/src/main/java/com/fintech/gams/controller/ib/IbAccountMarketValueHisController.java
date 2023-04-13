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
import com.fintech.gams.ib.entity.IbAccountMarketValueHis;
import com.fintech.gams.ib.service.IbAccountMarketValueHisService;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * IB账户市值历史表 控制器
 * <p>File：IbAccountMarketValueHisController.java </p>
 * <p>Title: IbAccountMarketValueHisController </p>
 * <p>Description:IbAccountMarketValueHisController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.IB)
@Api(description = "IB账户市值历史表")
public class IbAccountMarketValueHisController extends GenericController
{
    @Autowired(required = false)
    private IbAccountMarketValueHisService ibAccountMarketValueHisService;

    @PostMapping(value = "/accountMarketValueHis/accountSummaryList")
    @ApiOperation(value = "IB账户市值历史4账户概览", httpMethod = "POST")
    public JsonMessage accountMarketValueHis4AccountSummary(@ModelAttribute IbAccountMarketValueHis ibAccountMarketValueHis) throws BusinessException
    {
        ibAccountMarketValueHis.setAccountId(OnLineUserUtils.getId());
        // ibAccountMarketValueHis.setAccountCode("U8138565");
        ibAccountMarketValueHis.setCurrency("BASE"); // 本位币
        // 查询3个月内的历史数据
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -3); // 三个月前
        Date m3Before = calendar.getTime();
        String mon3Before = format.format(m3Before);
        ibAccountMarketValueHis.setCreateDateStart(Long.valueOf(mon3Before));

        List<IbAccountMarketValueHis> result = ibAccountMarketValueHisService.findList(ibAccountMarketValueHis);

        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/accountMarketValueHis/investSummaryList")
    @ApiOperation(value = "IB账户市值历史4投资概览", httpMethod = "POST")
    public JsonMessage accountMarketValueHis4InvestSummary(@ModelAttribute IbAccountMarketValueHis ibAccountMarketValueHis) throws BusinessException
    {
        ibAccountMarketValueHis.setAccountId(OnLineUserUtils.getId());
        // ibAccountMarketValueHis.setAccountCode("U8138565");
        ibAccountMarketValueHis.setCurrency("BASE"); // 本位币

        List<IbAccountMarketValueHis> result = ibAccountMarketValueHisService.findList(ibAccountMarketValueHis);

        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
