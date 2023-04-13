/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.quote.entity.QuoteForeignEx;

/**
 * 外汇行情 服务接口
 * <p>File：QuoteForeignExService.java </p>
 * <p>Title: QuoteForeignExService </p>
 * <p>Description:QuoteForeignExService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface QuoteForeignExService extends GenericService<QuoteForeignEx>{
    /**
     * 根据stockCode和quoteDate查询
     * @param stockCode
     * @param quoteDate
     * @return
     */
    QuoteForeignEx selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate);

}
