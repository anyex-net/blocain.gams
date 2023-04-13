/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.stock.entity.StockFund;

/**
 * 证券基金信息 持久层接口
 * <p>File：StockFundMapper.java </p>
 * <p>Title: StockFundMapper </p>
 * <p>Description:StockFundMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface StockFundMapper extends GenericMapper<StockFund>
{

}
