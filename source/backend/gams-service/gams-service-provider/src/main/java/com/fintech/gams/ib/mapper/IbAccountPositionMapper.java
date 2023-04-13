/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.ib.entity.IbAccountPosition;

/**
 * IB账户持仓表 持久层接口
 * <p>File：IbAccountPositionMapper.java </p>
 * <p>Title: IbAccountPositionMapper </p>
 * <p>Description:IbAccountPositionMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface IbAccountPositionMapper extends GenericMapper<IbAccountPosition>
{

}
