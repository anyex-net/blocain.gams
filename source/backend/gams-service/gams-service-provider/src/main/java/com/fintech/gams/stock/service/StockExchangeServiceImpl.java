/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.stock.entity.StockExchange;
import com.fintech.gams.stock.mapper.StockExchangeMapper;

/**
 * 证券交易所信息 服务实现类
 * <p>File：StockExchangeServiceImpl.java </p>
 * <p>Title: StockExchangeServiceImpl </p>
 * <p>Description:StockExchangeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class StockExchangeServiceImpl extends GenericServiceImpl<StockExchange> implements StockExchangeService
{

    protected StockExchangeMapper stockExchangeMapper;

    @Autowired(required = false)
    public StockExchangeServiceImpl(StockExchangeMapper stockExchangeMapper)
    {
        super(stockExchangeMapper);
        this.stockExchangeMapper = stockExchangeMapper;
    }
}
