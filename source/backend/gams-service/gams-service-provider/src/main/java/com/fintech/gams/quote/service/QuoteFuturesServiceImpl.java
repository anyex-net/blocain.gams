/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.quote.entity.QuoteFutures;
import com.fintech.gams.quote.mapper.QuoteFuturesMapper;

/**
 * 期货行情 服务实现类
 * <p>File：QuoteFuturesServiceImpl.java </p>
 * <p>Title: QuoteFuturesServiceImpl </p>
 * <p>Description:QuoteFuturesServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = QuoteFuturesService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class QuoteFuturesServiceImpl extends GenericServiceImpl<QuoteFutures> implements QuoteFuturesService
{

    protected QuoteFuturesMapper quoteFuturesMapper;

    @Autowired(required = false)
    public QuoteFuturesServiceImpl(QuoteFuturesMapper quoteFuturesMapper)
    {
        super(quoteFuturesMapper);
        this.quoteFuturesMapper = quoteFuturesMapper;
    }

    @Override
    public QuoteFutures selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate) {
        QuoteFutures result = quoteFuturesMapper.selectQuoteByStockCodeAndQuoteDate(stockCode, quoteDate);
        return result;
    }
}
