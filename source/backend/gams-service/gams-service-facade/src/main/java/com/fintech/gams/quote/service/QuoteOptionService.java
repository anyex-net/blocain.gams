/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.quote.entity.QuoteOption;

/**
 * 期权行情 服务接口
 * <p>File：QuoteOptionService.java </p>
 * <p>Title: QuoteOptionService </p>
 * <p>Description:QuoteOptionService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
    public interface QuoteOptionService extends GenericService<QuoteOption>{

    /**
     * 根据stockCode和quoteDate查询
     * @param stockCode
     * @param quoteDate
     * @return
     */
    QuoteOption selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate);
}
