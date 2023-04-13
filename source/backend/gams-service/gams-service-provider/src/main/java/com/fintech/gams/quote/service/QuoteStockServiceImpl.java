/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.quote.entity.QuoteStock;
import com.fintech.gams.quote.mapper.QuoteStockMapper;

/**
 * 股票行情 服务实现类
 * <p>File：QuoteStockServiceImpl.java </p>
 * <p>Title: QuoteStockServiceImpl </p>
 * <p>Description:QuoteStockServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class QuoteStockServiceImpl extends GenericServiceImpl<QuoteStock> implements QuoteStockService
{

    protected QuoteStockMapper quoteStockMapper;

    @Autowired(required = false)
    public QuoteStockServiceImpl(QuoteStockMapper quoteStockMapper)
    {
        super(quoteStockMapper);
        this.quoteStockMapper = quoteStockMapper;
    }

    @Override
    public QuoteStock selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate) {
        QuoteStock result = quoteStockMapper.selectQuoteByStockCodeAndQuoteDate(stockCode, quoteDate);
        return result;
    }
}
