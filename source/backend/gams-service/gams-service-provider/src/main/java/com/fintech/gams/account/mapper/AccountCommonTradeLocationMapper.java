/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ACCOUNT_COMMON_TRADE_LOCATION 持久层接口
 * <p>File：AccountCommonTradeLocationMapper.java </p>
 * <p>Title: AccountCommonTradeLocationMapper </p>
 * <p>Description:AccountCommonTradeLocationMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCommonTradeLocationMapper extends GenericMapper<AccountCommonTradeLocation>
{
    List<AccountCommonTradeLocation> findByTansactionType(String tansactionType);
}
