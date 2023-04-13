/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.quote.entity.QuoteFund;

/**
 * 基金行情 服务接口
 * <p>File：QuoteFundService.java </p>
 * <p>Title: QuoteFundService </p>
 * <p>Description:QuoteFundService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface QuoteFundService extends GenericService<QuoteFund>{

    /**
     * 根据stockCode和quoteDate查询
     * @param stockCode
     * @param quoteDate
     * @return
     */
    QuoteFund selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate);
}
