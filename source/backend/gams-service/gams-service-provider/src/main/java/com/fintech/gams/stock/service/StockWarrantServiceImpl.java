/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.SerialnoUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.stock.entity.StockWarrant;
import com.fintech.gams.stock.mapper.StockWarrantMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 证券权证信息 服务实现类
 * <p>File：StockWarrantServiceImpl.java </p>
 * <p>Title: StockWarrantServiceImpl </p>
 * <p>Description:StockWarrantServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class StockWarrantServiceImpl extends GenericServiceImpl<StockWarrant> implements StockWarrantService {

    protected StockWarrantMapper stockWarrantMapper;

    @Autowired(required = false)
    public StockWarrantServiceImpl(StockWarrantMapper stockWarrantMapper)
    {
        super(stockWarrantMapper);
        this.stockWarrantMapper = stockWarrantMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(StockWarrant stockWarrant) throws BusinessException {
        this.beanValidator(stockWarrant);
        if (null == stockWarrant.getId()) {
            stockWarrant.setId(SerialnoUtils.buildPrimaryKey());
            // 判断数据是否重复
            StockWarrant stockWarrant1 = new StockWarrant();
            stockWarrant1.setStockInfoId(stockWarrant.getStockInfoId());
            stockWarrant1.setId(stockWarrant.getId());
            List<StockWarrant> list = stockWarrantMapper.findList(stockWarrant1);
            if (list != null && list.size() > 0) {
                log.error("stockWarrant插入 error: 违反唯一约束条件，数据库中已存在该数据");
                throw new BusinessException("违反唯一约束条件，数据库中已存在该数据");
            }
            return stockWarrantMapper.insert(stockWarrant);
        } else {
            return stockWarrantMapper.updateByPrimaryKeySelective(stockWarrant);
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = true)
    public PaginateResult<StockWarrant> searchStockWarrant(Pagination pagin, StockWarrant entity) throws BusinessException
    {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<StockWarrant> pageInfo = PageInfo.of(stockWarrantMapper.findListWarrant(entity));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
