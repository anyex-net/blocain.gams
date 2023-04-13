/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.ib.entity.IbAccountTrade;
import com.ib.client.Contract;
import com.ib.client.Execution;

/**
 * IB账户交易流水表 服务接口
 * <p>File：IbAccountTradeService.java </p>
 * <p>Title: IbAccountTradeService </p>
 * <p>Description:IbAccountTradeService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface IbAccountTradeService extends GenericService<IbAccountTrade>{

    /**
     *
     * @param reqId
     * @param contract
     * @param execution
     */
    void ibMessageProcessExecDetails(int reqId, Contract contract, Execution execution);
}
