/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.quote.entity.QuoteFund;
import com.fintech.gams.quote.mapper.QuoteFundMapper;

/**
 * 基金行情 服务实现类
 * <p>File：QuoteFundServiceImpl.java </p>
 * <p>Title: QuoteFundServiceImpl </p>
 * <p>Description:QuoteFundServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = QuoteFundService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class QuoteFundServiceImpl extends GenericServiceImpl<QuoteFund> implements QuoteFundService
{

    protected QuoteFundMapper quoteFundMapper;

    @Autowired(required = false)
    public QuoteFundServiceImpl(QuoteFundMapper quoteFundMapper)
    {
        super(quoteFundMapper);
        this.quoteFundMapper = quoteFundMapper;
    }

    @Override
    public QuoteFund selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate) {
        QuoteFund result = quoteFundMapper.selectQuoteByStockCodeAndQuoteDate(stockCode, quoteDate);
        return result;
    }
}
