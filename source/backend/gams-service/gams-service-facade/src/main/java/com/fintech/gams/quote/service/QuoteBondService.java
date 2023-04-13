/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.quote.entity.QuoteBond;

/**
 * 债券行情 服务接口
 * <p>File：QuoteBondService.java </p>
 * <p>Title: QuoteBondService </p>
 * <p>Description:QuoteBondService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface QuoteBondService extends GenericService<QuoteBond>{

    /**
     * 根据stockCode和quoteDate查询
     * @param stockCode
     * @param quoteDate
     * @return
     */
    QuoteBond selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate);
}
