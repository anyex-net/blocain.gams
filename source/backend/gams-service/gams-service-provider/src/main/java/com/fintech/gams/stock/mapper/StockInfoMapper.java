/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.stock.entity.StockInfo;

/**
 * 证券信息 持久层接口
 * <p>File：StockInfoMapper.java </p>
 * <p>Title: StockInfoMapper </p>
 * <p>Description:StockInfoMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface StockInfoMapper extends GenericMapper<StockInfo>
{

    /**
     * 根据证券编码取证券信息
     * @param stockCode
     * @return
     */
    StockInfo findByStockInfoCode(String stockCode);
}
