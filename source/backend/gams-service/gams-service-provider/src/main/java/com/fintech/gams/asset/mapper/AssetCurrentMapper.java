/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.asset.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.asset.entity.AssetCurrent;
import com.fintech.gams.bean.GenericMapper;

/**
 * 资产流水表 持久层接口
 * <p>File：AssetCurrentMapper.java </p>
 * <p>Title: AssetCurrentMapper </p>
 * <p>Description:AssetCurrentMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AssetCurrentMapper extends GenericMapper<AssetCurrent>
{

}
