/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.ib.client.TickAttrib;

/**
 * IB Tick 服务接口
 * <p>File：IbTickService.java </p>
 * <p>Title: IbTickService </p>
 * <p>Description:IbTickService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface IbTickService {

    /**
     *
     * @param tickerId
     * @param field
     * @param price
     * @param attribs
     */
    void ibMessageProcessTickPrice(int tickerId, int field, double price, TickAttrib attribs);

    /**
     *
     * @param tickerId
     * @param field
     * @param size
     */
    void ibMessageProcessTickSize(int tickerId, int field, int size);

    /**
     *
     * @param tickerId
     * @param tickType
     * @param value
     */
    void ibMessageProcessTickString(int tickerId, int tickType, String value);

    /**
     *
     * @param tickerId
     * @param field
     * @param impliedVol
     * @param delta
     * @param optPrice
     * @param pvDividend
     * @param gamma
     * @param vega
     * @param theta
     * @param undPrice
     */
    void ibMessageProcessTickOptionComputation(int tickerId, int field, double impliedVol, double delta, double optPrice,
                                               double pvDividend, double gamma, double vega, double theta, double undPrice);
}
