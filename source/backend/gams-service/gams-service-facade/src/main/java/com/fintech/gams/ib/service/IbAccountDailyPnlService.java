/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.ib.entity.IbAccountDailyPnl;

/**
 * IB账户当日盈亏 服务接口
 * <p>File：IbAccountDailyPnlService.java </p>
 * <p>Title: IbAccountDailyPnlService </p>
 * <p>Description:IbAccountDailyPnlService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface IbAccountDailyPnlService extends GenericService<IbAccountDailyPnl>{

    /**
     *
     * @param reqId
     * @param dailyPnL
     * @param unrealizedPnL
     * @param realizedPnL
     */
    void ibMessageProcessPnL(int reqId, double dailyPnL, double unrealizedPnL, double realizedPnL);
}
