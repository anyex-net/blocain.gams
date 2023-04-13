/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.ib.entity.IbAccountBalanceMargin;

/**
 * IB账户资金保证金表 服务接口
 * <p>File：IbAccountBalanceMarginService.java </p>
 * <p>Title: IbAccountBalanceMarginService </p>
 * <p>Description:IbAccountBalanceMarginService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface IbAccountBalanceMarginService extends GenericService<IbAccountBalanceMargin>{

    /**
     *
     * @param key
     * @param value
     * @param currency
     * @param accountCode
     */
    void ibMessageProcess(String key, String value, String currency, String accountCode);
}
