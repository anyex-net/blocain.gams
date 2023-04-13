/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.quote.entity.QuoteWarrant;
import com.fintech.gams.quote.mapper.QuoteWarrantMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * 权证行情 服务实现类
 * <p>File：QuoteWarrantServiceImpl.java </p>
 * <p>Title: QuoteWarrantServiceImpl </p>
 * <p>Description:QuoteWarrantServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class QuoteWarrantServiceImpl extends GenericServiceImpl<QuoteWarrant> implements QuoteWarrantService
{

    protected QuoteWarrantMapper quoteWarrantMapper;

    @Autowired(required = false)
    public QuoteWarrantServiceImpl(QuoteWarrantMapper quoteWarrantMapper)
    {
        super(quoteWarrantMapper);
        this.quoteWarrantMapper = quoteWarrantMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(QuoteWarrant quoteWarrant) throws BusinessException
    {
        this.beanValidator(quoteWarrant);
        if (null == quoteWarrant.getId())
        {
            quoteWarrant.setId(SerialnoUtils.buildPrimaryKey());
            // 判断数据是否重复
            QuoteWarrant quoteWarrant1 = new QuoteWarrant();
            quoteWarrant1.setStockinfoId(quoteWarrant.getStockinfoId());
            quoteWarrant1.setId(quoteWarrant.getId());
            quoteWarrant1.setQuoteDate(quoteWarrant.getQuoteDate());
            List<QuoteWarrant> list = quoteWarrantMapper.findList(quoteWarrant1);
            if(list != null && list.size() > 0){
                log.error("quoteWarrant插入 error: 违反唯一约束条件，数据库中已存在该数据");
                throw new BusinessException("已存在该行情信息");
            }
            return quoteWarrantMapper.insert(quoteWarrant);
        }
        else
        {
            return quoteWarrantMapper.updateByPrimaryKeySelective(quoteWarrant);
        }
    }

    @Override
    public QuoteWarrant selectQuoteByStockCodeAndQuoteDate(String stockCode, Long quoteDate) {
        QuoteWarrant result = quoteWarrantMapper.selectQuoteByStockCodeAndQuoteDate(stockCode, quoteDate);
        return result;
    }
}
