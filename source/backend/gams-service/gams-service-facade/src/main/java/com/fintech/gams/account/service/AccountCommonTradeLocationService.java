/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.bean.GenericService;

import java.util.List;

/**
 * ACCOUNT_COMMON_TRADE_LOCATION 服务接口
 * <p>File：AccountCommonTradeLocationService.java </p>
 * <p>Title: AccountCommonTradeLocationService </p>
 * <p>Description:AccountCommonTradeLocationService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountCommonTradeLocationService extends GenericService<AccountCommonTradeLocation>{
    /**
     * 根据TansactionType(交易类型)取 账户交易经验地点
     * @param tansactionType
     * @return
     */
    List<AccountCommonTradeLocation> findByTansactionType(String tansactionType);
}
