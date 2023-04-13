/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.stock.entity.StockHotConfig;
import com.fintech.gams.stock.mapper.StockHotConfigMapper;

/**
 * 证券热门推荐配置 服务实现类
 * <p>File：StockHotConfigServiceImpl.java </p>
 * <p>Title: StockHotConfigServiceImpl </p>
 * <p>Description:StockHotConfigServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class StockHotConfigServiceImpl extends GenericServiceImpl<StockHotConfig> implements StockHotConfigService
{

    protected StockHotConfigMapper stockHotConfigMapper;

    @Autowired(required = false)
    public StockHotConfigServiceImpl(StockHotConfigMapper stockHotConfigMapper)
    {
        super(stockHotConfigMapper);
        this.stockHotConfigMapper = stockHotConfigMapper;
    }
}
