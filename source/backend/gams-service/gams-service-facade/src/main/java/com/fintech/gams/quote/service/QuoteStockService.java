/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.quote.entity.QuoteStock;

/**
 * 股票行情 服务接口
 * <p>File：QuoteStockService.java </p>
 * <p>Title: QuoteStockService </p>
 * <p>Description:QuoteStockService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface QuoteStockService extends GenericService<QuoteStock> {

    /**
     * 根据stockCode和quoteDate查询
     * @param stockCode
     * @param quoteDate
     * @return
     */
    QuoteStock selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate);

}
