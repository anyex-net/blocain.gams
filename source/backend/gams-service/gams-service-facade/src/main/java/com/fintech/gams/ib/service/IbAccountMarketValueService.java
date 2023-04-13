/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.ib.entity.IbAccountMarketValue;

import java.util.List;

/**
 * IB账户持仓市值表 服务接口
 * <p>File：IbAccountMarketValueService.java </p>
 * <p>Title: IbAccountMarketValueService </p>
 * <p>Description:IbAccountMarketValueService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface IbAccountMarketValueService extends GenericService<IbAccountMarketValue>{

    /**
     *
     * @param reqId
     * @param accountCode
     * @param modelCode
     * @param key
     * @param value
     * @param currency
     */
    void ibMessageProcessAccountUpdateMulti(int reqId, String accountCode, String modelCode, String key, String value, String currency);

    /**
     *
     * @param key
     * @param value
     * @param currency
     * @param accountCode
     */
    void ibMessageProcessUpdateAccountValue(String key, String value, String currency, String accountCode);

    /**
     * 查询汇率
     * @param accountId
     * @return
     */
    List<IbAccountMarketValue> findExchangeRateList(Long accountId);
}
