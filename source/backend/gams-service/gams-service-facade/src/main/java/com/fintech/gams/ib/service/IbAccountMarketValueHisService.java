/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.ib.entity.IbAccountMarketValueHis;

/**
 * IB账户市值历史表 服务接口
 * <p>File：IbAccountMarketValueHisService.java </p>
 * <p>Title: IbAccountMarketValueHisService </p>
 * <p>Description:IbAccountMarketValueHisService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface IbAccountMarketValueHisService extends GenericService<IbAccountMarketValueHis>{

    /**
     *
     * @param reqId
     * @param accountCode
     * @param modelCode
     * @param key
     * @param value
     * @param currency
     */
    void ibMessageProcessAccountUpdateMultiHis(int reqId, String accountCode, String modelCode, String key, String value, String currency);

    /**
     *
     * @param key
     * @param value
     * @param currency
     * @param accountCode
     */
    void ibMessageProcessUpdateAccountValueHis(String key, String value, String currency, String accountCode);

}
