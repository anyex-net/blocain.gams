/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.ib.entity.IbAccountPosition;
import com.ib.client.Contract;

/**
 * IB账户持仓表 服务接口
 * <p>File：IbAccountPositionService.java </p>
 * <p>Title: IbAccountPositionService </p>
 * <p>Description:IbAccountPositionService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface IbAccountPositionService extends GenericService<IbAccountPosition>{

    /**
     *
     * @param contract
     * @param position
     * @param marketPrice
     * @param marketValue
     * @param averageCost
     * @param unrealizedPNL
     * @param realizedPNL
     * @param accountCode
     */
    void ibMessageProcessPortfolio(Contract contract, double position, double marketPrice, double marketValue, double averageCost,
                          double unrealizedPNL, double realizedPNL, String accountCode);

    /**
     *
     * @param accountCode
     * @param contract
     * @param position
     * @param averageCost
     */
    void ibMessageProcessPosition(String accountCode, Contract contract, double position, double averageCost);

    /**
     *
     * @param reqId
     * @param accountCode
     * @param modelCode
     * @param contract
     * @param position
     * @param averageCost
     */
    void ibMessageProcessPositionMulti(int reqId, String accountCode, String modelCode, Contract contract, double position, double averageCost);
}
