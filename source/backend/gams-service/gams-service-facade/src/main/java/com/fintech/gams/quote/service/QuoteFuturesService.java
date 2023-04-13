/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.quote.entity.QuoteFutures;

/**
 * 期货行情 服务接口
 * <p>File：QuoteFuturesService.java </p>
 * <p>Title: QuoteFuturesService </p>
 * <p>Description:QuoteFuturesService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface QuoteFuturesService extends GenericService<QuoteFutures>{

    /**
     * 根据stockCode和quoteDate查询
     * @param stockCode
     * @param quoteDate
     * @return
     */
    QuoteFutures selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate);
}
