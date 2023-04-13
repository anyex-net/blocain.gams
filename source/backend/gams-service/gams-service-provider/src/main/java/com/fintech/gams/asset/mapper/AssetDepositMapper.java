/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.asset.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.asset.entity.AssetDeposit;
import com.fintech.gams.bean.GenericMapper;

/**
 * 充值入金记录表 持久层接口
 * <p>File：AssetDepositMapper.java </p>
 * <p>Title: AssetDepositMapper </p>
 * <p>Description:AssetDepositMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AssetDepositMapper extends GenericMapper<AssetDeposit>
{

}
