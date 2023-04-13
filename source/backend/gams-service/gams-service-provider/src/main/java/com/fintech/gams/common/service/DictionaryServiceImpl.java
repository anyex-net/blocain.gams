/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import java.util.List;

import com.fintech.gams.common.model.HelpCenterTreeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.mapper.DictionaryMapper;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.StringUtils;

/**
 * 字典数据 服务实现类
 * <p>File：DictionaryServiceImpl.java </p>
 * <p>Title: DictionaryServiceImpl </p>
 * <p>Description:DictionaryServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DictionaryServiceImpl extends GenericServiceImpl<Dictionary> implements DictionaryService
{
    protected DictionaryMapper dictionaryMapper;
    
    @Autowired(required = false)
    public DictionaryServiceImpl(DictionaryMapper dictionaryMapper)
    {
        super(dictionaryMapper);
        this.dictionaryMapper = dictionaryMapper;
    }
    
    @Override
    public List<Dictionary> findByCode(String dictCode, String lang) throws BusinessException
    {
        if (StringUtils.isBlank(dictCode) || StringUtils.isBlank(lang))
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return dictionaryMapper.findByCode(dictCode, lang);
    }
    
    @Override
    public Dictionary findByValue(String valueCode) throws BusinessException
    {
        return dictionaryMapper.findByValue(valueCode);
    }

    @Override
    public List<Dictionary> findByCodeArr(String[] dictCodeArr, String lang) throws BusinessException {
        return dictionaryMapper.findByCodeArr(dictCodeArr,lang);
    }

    @Override
    public Dictionary findDictionary(String dictCode,String valueCode, String lang) throws BusinessException {
        if (StringUtils.isBlank(dictCode) || StringUtils.isBlank(valueCode) || StringUtils.isBlank(lang))
        { return new Dictionary(); }
        return dictionaryMapper.findDictionary(dictCode,valueCode,lang);
    }

    @Override
    public List<HelpCenterTreeModel> findByCodeAndTree(String dictCode, String lang) throws BusinessException {
        if (StringUtils.isBlank(dictCode) || StringUtils.isBlank(lang))
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return dictionaryMapper.findByCodeAndTree(dictCode, lang);
    }

    @Override
    public HelpCenterTreeModel findByCodeAndValue(String dictCode, String dictValue, String lang) throws BusinessException {
        if (StringUtils.isBlank(dictCode) || StringUtils.isBlank(dictValue) || StringUtils.isBlank(lang))
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return dictionaryMapper.findByCodeAndValue(dictCode, dictValue, lang);
    }
}
