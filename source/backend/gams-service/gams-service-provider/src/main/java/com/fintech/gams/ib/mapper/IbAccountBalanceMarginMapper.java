/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.ib.entity.IbAccountBalanceMargin;

/**
 * IB账户资金保证金表 持久层接口
 * <p>File：IbAccountBalanceMarginMapper.java </p>
 * <p>Title: IbAccountBalanceMarginMapper </p>
 * <p>Description:IbAccountBalanceMarginMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface IbAccountBalanceMarginMapper extends GenericMapper<IbAccountBalanceMargin>
{

}
