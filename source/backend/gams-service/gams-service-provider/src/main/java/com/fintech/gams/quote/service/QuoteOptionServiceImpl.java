/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import com.fintech.gams.quote.entity.QuoteFutures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.quote.entity.QuoteOption;
import com.fintech.gams.quote.mapper.QuoteOptionMapper;

/**
 * 期权行情 服务实现类
 * <p>File：QuoteOptionServiceImpl.java </p>
 * <p>Title: QuoteOptionServiceImpl </p>
 * <p>Description:QuoteOptionServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = QuoteOptionService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class QuoteOptionServiceImpl extends GenericServiceImpl<QuoteOption> implements QuoteOptionService
{

    protected QuoteOptionMapper quoteOptionMapper;

    @Autowired(required = false)
    public QuoteOptionServiceImpl(QuoteOptionMapper quoteOptionMapper)
    {
        super(quoteOptionMapper);
        this.quoteOptionMapper = quoteOptionMapper;
    }

    @Override
    public QuoteOption selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate) {
        QuoteOption result = quoteOptionMapper.selectQuoteByStockCodeAndQuoteDate(stockCode, quoteDate);
        return result;
    }
}
