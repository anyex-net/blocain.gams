/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.stock.entity.StockWarrant;

import java.util.List;

/**
 * 证券权证信息 持久层接口
 * <p>File：StockWarrantMapper.java </p>
 * <p>Title: StockWarrantMapper </p>
 * <p>Description:StockWarrantMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface StockWarrantMapper extends GenericMapper<StockWarrant>
{
    /**
     * 查询证券权证信息
     * @param stockWarrant
     * @return
     */
    List<StockWarrant> findListWarrant(StockWarrant stockWarrant);
}
