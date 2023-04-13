/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.quote.entity.QuoteFutures;
import org.apache.ibatis.annotations.Param;

/**
 * 期货行情 持久层接口
 * <p>File：QuoteFuturesMapper.java </p>
 * <p>Title: QuoteFuturesMapper </p>
 * <p>Description:QuoteFuturesMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface QuoteFuturesMapper extends GenericMapper<QuoteFutures>
{
    /**
     * 根据StockCode和quoteDate查询
     * @param stockCode
     * @param quoteDate
     * @return
     */
    QuoteFutures selectQuoteByStockCodeAndQuoteDate(@Param("stockCode") String stockCode, @Param("quoteDate") Long quoteDate);
}
