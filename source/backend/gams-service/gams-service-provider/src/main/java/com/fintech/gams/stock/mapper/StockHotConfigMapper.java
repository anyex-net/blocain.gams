/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.stock.entity.StockHotConfig;

/**
 * 证券热门推荐配置 持久层接口
 * <p>File：StockHotConfigMapper.java </p>
 * <p>Title: StockHotConfigMapper </p>
 * <p>Description:StockHotConfigMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface StockHotConfigMapper extends GenericMapper<StockHotConfig>
{

}
