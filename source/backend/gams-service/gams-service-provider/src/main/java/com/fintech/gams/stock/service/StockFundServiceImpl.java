/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.stock.entity.StockFund;
import com.fintech.gams.stock.mapper.StockFundMapper;

/**
 * 证券基金信息 服务实现类
 * <p>File：StockFundServiceImpl.java </p>
 * <p>Title: StockFundServiceImpl </p>
 * <p>Description:StockFundServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class StockFundServiceImpl extends GenericServiceImpl<StockFund> implements StockFundService
{

    protected StockFundMapper stockFundMapper;

    @Autowired(required = false)
    public StockFundServiceImpl(StockFundMapper stockFundMapper)
    {
        super(stockFundMapper);
        this.stockFundMapper = stockFundMapper;
    }
}
