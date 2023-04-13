/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.stock.entity.StockInfo;
import com.fintech.gams.stock.mapper.StockInfoMapper;

/**
 * 证券信息 服务实现类
 * <p>File：StockInfoServiceImpl.java </p>
 * <p>Title: StockInfoServiceImpl </p>
 * <p>Description:StockInfoServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class StockInfoServiceImpl extends GenericServiceImpl<StockInfo> implements StockInfoService
{
    protected StockInfoMapper stockInfoMapper;
    
    @Autowired(required = false)
    public StockInfoServiceImpl(StockInfoMapper stockInfoMapper)
    {
        super(stockInfoMapper);
        this.stockInfoMapper = stockInfoMapper;
    }
    //
    // @Override
    // public int save(StockInfo info) throws BusinessException
    // {
    // this.beanValidator(info);
    // if (null == info.getId())
    // {
    // validbusinessParams(info);
    // info.setId(SerialnoUtils.buildPrimaryKey());
    // return stockInfoMapper.insert(info);
    // }
    // else
    // {
    // StockInfo entity = stockInfoMapper.selectByPrimaryKey(info.getId());
    // if (!entity.getStockCode().equals(info.getStockCode()))
    // {
    // validbusinessParams(info);
    // }
    // return stockInfoMapper.updateByPrimaryKeySelective(info);
    // }
    // }
    //
    // /**
    // * 验证业务参数
    // * @param info
    // * @throws BusinessException
    // */
    // void validbusinessParams(StockInfo info) throws BusinessException
    // {
    // if (null != stockInfoMapper.findByStockInfoCode(info.getStockCode()))
    // {//
    // throw new BusinessException(CommonEnums.PARAMS_VALID_ERR.code, "证券编码不可重复！");
    // }
    // }
}
