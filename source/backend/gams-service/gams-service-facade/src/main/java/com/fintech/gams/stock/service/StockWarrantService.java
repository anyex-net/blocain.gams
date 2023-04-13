/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.stock.entity.StockWarrant;

/**
 * 证券权证信息 服务接口
 * <p>File：StockWarrantService.java </p>
 * <p>Title: StockWarrantService </p>
 * <p>Description:StockWarrantService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface StockWarrantService extends GenericService<StockWarrant>{

    /**
     * 查询证券权证信息
     * @param pagin
     * @param model
     * @return
     * @throws BusinessException
     */
    PaginateResult<StockWarrant> searchStockWarrant(Pagination pagin, StockWarrant model) throws BusinessException;
}
