/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.quote.entity.QuoteStock;
import org.apache.ibatis.annotations.Param;

/**
 * 股票行情 持久层接口
 * <p>File：QuoteStockMapper.java </p>
 * <p>Title: QuoteStockMapper </p>
 * <p>Description:QuoteStockMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface QuoteStockMapper extends GenericMapper<QuoteStock>
{
    /**
     * 根据StockCode和quoteDate查询
     * @param stockCode
     * @param quoteDate
     * @return
     */
    QuoteStock selectQuoteByStockCodeAndQuoteDate(@Param("stockCode") String stockCode, @Param("quoteDate") Long quoteDate);
}
