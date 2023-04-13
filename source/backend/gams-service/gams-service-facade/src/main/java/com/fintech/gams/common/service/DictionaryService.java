/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import java.util.List;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.model.HelpCenterTreeModel;
import com.fintech.gams.exception.BusinessException;

/**
 * 字典数据 服务接口
 * <p>File：DictionaryService.java </p>
 * <p>Title: DictionaryService </p>
 * <p>Description:DictionaryService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface DictionaryService extends GenericService<Dictionary>
{
    /**
     * 根据字典编码和语言取字典项
     * @param dictCode
     * @param lang
     * @return
     * @throws BusinessException
     */
    List<Dictionary> findByCode(String dictCode, String lang) throws BusinessException;
    
    /**
     * 根据字典项编码取字典详情
     * @param valueCode
     * @return
     * @throws BusinessException
     */
    Dictionary findByValue(String valueCode) throws BusinessException;

    /**
     * 根据 指定 字典键值和语言取字典项
     * @param dictCodeArr
     * @param lang
     * @return
     * @throws BusinessException
     */
    List<Dictionary>  findByCodeArr(String[] dictCodeArr, String lang) throws BusinessException;

    /**
     * 根据字典项编码和语言取字典详情
     * @param valueCode
     * @param lang
     * @return
     */
    Dictionary findDictionary(String dictCode,String valueCode,String lang) throws BusinessException;

    /**
     * 根据字典编码和语言取字典项
     * @param dictCode
     * @param lang
     * @return
     * @throws BusinessException
     */
    List<HelpCenterTreeModel> findByCodeAndTree(String dictCode, String lang) throws BusinessException;

    /**
     * 根据字典编码 和字典键值 和语言取字典项
     * @param dictCode 字典编码
     * @param dictValue 字典键值
     * @param lang 语言
     * @return
     * @throws BusinessException
     */
    HelpCenterTreeModel findByCodeAndValue(String dictCode, String dictValue, String lang) throws BusinessException;
}
