/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.quote.entity.QuoteForeignEx;
import org.apache.ibatis.annotations.Param;

/**
 * 外汇行情 持久层接口
 * <p>File：QuoteForeignExMapper.java </p>
 * <p>Title: QuoteForeignExMapper </p>
 * <p>Description:QuoteForeignExMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface QuoteForeignExMapper extends GenericMapper<QuoteForeignEx>
{
    /**
     * 根据StockCode和quoteDate查询
     * @param stockCode
     * @param quoteDate
     * @return
     */
    QuoteForeignEx selectQuoteByStockCodeAndQuoteDate(@Param("stockCode") String stockCode, @Param("quoteDate") Long quoteDate);
}
