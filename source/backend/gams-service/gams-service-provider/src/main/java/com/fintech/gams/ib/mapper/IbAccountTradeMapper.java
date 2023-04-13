/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.ib.entity.IbAccountTrade;

/**
 * IB账户交易流水表 持久层接口
 * <p>File：IbAccountTradeMapper.java </p>
 * <p>Title: IbAccountTradeMapper </p>
 * <p>Description:IbAccountTradeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface IbAccountTradeMapper extends GenericMapper<IbAccountTrade>
{

}
