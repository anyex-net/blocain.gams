/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.ib.entity.IbAccountDailyPnl;

/**
 * IB账户当日盈亏 持久层接口
 * <p>File：IbAccountDailyPnlMapper.java </p>
 * <p>Title: IbAccountDailyPnlMapper </p>
 * <p>Description:IbAccountDailyPnlMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface IbAccountDailyPnlMapper extends GenericMapper<IbAccountDailyPnl>
{

}
