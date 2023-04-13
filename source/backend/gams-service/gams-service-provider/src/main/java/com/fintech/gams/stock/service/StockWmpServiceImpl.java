/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.stock.entity.StockWmp;
import com.fintech.gams.stock.mapper.StockWmpMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 证券理财产品信息 服务实现类
 * <p>File：StockWmpServiceImpl.java </p>
 * <p>Title: StockWmpServiceImpl </p>
 * <p>Description:StockWmpServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class StockWmpServiceImpl extends GenericServiceImpl<StockWmp> implements StockWmpService {

    protected StockWmpMapper stockWmpMapper;

    @Autowired(required = false)
    public StockWmpServiceImpl(StockWmpMapper stockWmpMapper)
    {
        super(stockWmpMapper);
        this.stockWmpMapper = stockWmpMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(StockWmp stockWmp) throws BusinessException {
        this.beanValidator(stockWmp);
        // 推荐的证券理财产品不得超过3个
        if(stockWmp.getIsRecommend() == true ){
            StockWmp stockWmp1 = new StockWmp();
            stockWmp1.setIsRecommend(true);
            List<StockWmp> stockWmpList = stockWmpMapper.findList(stockWmp1);
            // 判断是否是对数据库中已存在的理财数据进行修改
            boolean judge = true;
            if(stockWmpList.size() >= 3){
                for(StockWmp ele : stockWmpList){
                    if(ele.getStockInfoId().equals(stockWmp.getStockInfoId())){
                        judge = false;
                        break;
                    }
                }
                if(judge){
                    log.error("stockWmp插入 error: 推荐的证券理财产品不得超过3个");
                    throw new BusinessException("推荐的证券理财产品不得超过3个");
                }
            }
        }

        if (null == stockWmp.getId()) {
            stockWmp.setId(SerialnoUtils.buildPrimaryKey());
            // 判断数据是否重复
            StockWmp stockWmp2 = new StockWmp();
            stockWmp2.setId(stockWmp.getId());
            stockWmp2.setStockInfoId(stockWmp.getStockInfoId());
            List<StockWmp> list = stockWmpMapper.findList(stockWmp2);
            if (list != null && list.size() > 0) {
                log.error("stockWmp插入 error: 违反唯一约束条件，数据库中已存在该数据");
                throw new BusinessException("已存在该理财产品");
            }
            return stockWmpMapper.insert(stockWmp);
        } else {
            return stockWmpMapper.updateByPrimaryKeySelective(stockWmp);
        }
    }
}
