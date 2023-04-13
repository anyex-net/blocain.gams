/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.ib.entity.IbAccountMarketValueHis;

/**
 * IB账户市值历史表 持久层接口
 * <p>File：IbAccountMarketValueHisMapper.java </p>
 * <p>Title: IbAccountMarketValueHisMapper </p>
 * <p>Description:IbAccountMarketValueHisMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface IbAccountMarketValueHisMapper extends GenericMapper<IbAccountMarketValueHis>
{

}
