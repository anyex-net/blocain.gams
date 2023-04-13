/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import com.fintech.gams.quote.entity.QuoteStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.quote.entity.QuoteBond;
import com.fintech.gams.quote.mapper.QuoteBondMapper;

/**
 * 债券行情 服务实现类
 * <p>File：QuoteBondServiceImpl.java </p>
 * <p>Title: QuoteBondServiceImpl </p>
 * <p>Description:QuoteBondServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = QuoteBondService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class QuoteBondServiceImpl extends GenericServiceImpl<QuoteBond> implements QuoteBondService
{

    protected QuoteBondMapper quoteBondMapper;

    @Autowired(required = false)
    public QuoteBondServiceImpl(QuoteBondMapper quoteBondMapper)
    {
        super(quoteBondMapper);
        this.quoteBondMapper = quoteBondMapper;
    }

    @Override
    public QuoteBond selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate) {
        QuoteBond result = quoteBondMapper.selectQuoteByStockCodeAndQuoteDate(stockCode, quoteDate);
        return result;
    }
}
