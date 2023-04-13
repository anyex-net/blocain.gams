/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.quote.entity.QuoteOption;
import org.apache.ibatis.annotations.Param;

/**
 * 期权行情 持久层接口
 * <p>File：QuoteOptionMapper.java </p>
 * <p>Title: QuoteOptionMapper </p>
 * <p>Description:QuoteOptionMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface QuoteOptionMapper extends GenericMapper<QuoteOption>
{
    /**
     * 根据StockCode和quoteDate查询
     * @param stockCode
     * @param quoteDate
     * @return
     */
    QuoteOption selectQuoteByStockCodeAndQuoteDate(@Param("stockCode") String stockCode, @Param("quoteDate") Long quoteDate);
}
