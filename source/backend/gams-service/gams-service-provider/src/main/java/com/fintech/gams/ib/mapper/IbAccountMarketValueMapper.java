/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.ib.entity.IbAccountMarketValue;

import java.util.List;

/**
 * IB账户持仓市值表 持久层接口
 * <p>File：IbAccountMarketValueMapper.java </p>
 * <p>Title: IbAccountMarketValueMapper </p>
 * <p>Description:IbAccountMarketValueMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface IbAccountMarketValueMapper extends GenericMapper<IbAccountMarketValue>
{
    /**
     * 根据accountCode查询汇率
     * @param accountId
     * @return
     */
    List<IbAccountMarketValue> findExchangeRateList(Long accountId);
}
