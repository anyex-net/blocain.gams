/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.ib.entity.IbAccountFamily;
import com.ib.client.FamilyCode;

/**
 * IB账户家族表 服务接口
 * <p>File：IbAccountFamilyService.java </p>
 * <p>Title: IbAccountFamilyService </p>
 * <p>Description:IbAccountFamilyService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface IbAccountFamilyService extends GenericService<IbAccountFamily>{

    /**
     *
     * @param familyCodes
     */
    void ibMessageProcess(FamilyCode[] familyCodes);
}
