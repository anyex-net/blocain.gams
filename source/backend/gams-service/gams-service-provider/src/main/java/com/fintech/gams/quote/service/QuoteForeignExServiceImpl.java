/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.quote.entity.QuoteForeignEx;
import com.fintech.gams.quote.mapper.QuoteForeignExMapper;

/**
 * 外汇行情 服务实现类
 * <p>File：QuoteForeignExServiceImpl.java </p>
 * <p>Title: QuoteForeignExServiceImpl </p>
 * <p>Description:QuoteForeignExServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class QuoteForeignExServiceImpl extends GenericServiceImpl<QuoteForeignEx> implements QuoteForeignExService
{

    protected QuoteForeignExMapper quoteForeignexMapper;

    @Autowired(required = false)
    public QuoteForeignExServiceImpl(QuoteForeignExMapper quoteForeignexMapper)
    {
        super(quoteForeignexMapper);
        this.quoteForeignexMapper = quoteForeignexMapper;
    }

    @Override
    public QuoteForeignEx selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate) {
        QuoteForeignEx result = quoteForeignexMapper.selectQuoteByStockCodeAndQuoteDate(stockCode, quoteDate);
        return result;
    }
}
