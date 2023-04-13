/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.stock.entity.StockExchange;

/**
 * 证券交易所信息 持久层接口
 * <p>File：StockExchangeMapper.java </p>
 * <p>Title: StockExchangeMapper </p>
 * <p>Description:StockExchangeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface StockExchangeMapper extends GenericMapper<StockExchange>
{

}
