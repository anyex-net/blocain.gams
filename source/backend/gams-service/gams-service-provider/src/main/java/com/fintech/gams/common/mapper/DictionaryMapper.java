/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.mapper;

import java.util.List;

import com.fintech.gams.common.model.HelpCenterTreeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.common.entity.Dictionary;

/**
 * 字典数据 持久层接口
 * <p>File：DictionaryMapper.java </p>
 * <p>Title: DictionaryMapper </p>
 * <p>Description:DictionaryMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface DictionaryMapper extends GenericMapper<Dictionary>
{
    /**
     * 根据字典编码和语言取字典项
     * @param dictCode
     * @param lang
     * @return
     */
    List<Dictionary> findByCode(@Param("dictCode") String dictCode, @Param("lang") String lang);
    
    /**
     * 根据字典项编码取字典详情
     * @param valueCode
     * @return
     */
    Dictionary findByValue(String valueCode);

    /**
     * 根据 指定 字典键值和语言取字典项
     * @param dictCodeArr
     * @param lang
     * @return
     */
    List<Dictionary> findByCodeArr(@Param("dictCode") String[] dictCodeArr, @Param("lang") String lang);

    /**
     * 根据字典项编码和语言取字典详情
     * @param valueCode
     * @param lang
     * @return
     */
    Dictionary findDictionary(@Param("dictCode")String dictCode,@Param("valueCode") String valueCode, @Param("lang") String lang);

    /**
     * 根据字典编码和语言取字典项
     * @param dictCode
     * @param lang
     * @return
     */
    List<HelpCenterTreeModel> findByCodeAndTree(@Param("dictCode") String dictCode, @Param("lang") String lang);

    /**
     * 根据字典编码 和字典键值 和语言取字典项
     * @param dictCode
     * @param dictValue
     * @param lang
     * @return
     */
    HelpCenterTreeModel findByCodeAndValue(@Param("dictCode") String dictCode, @Param("dictValue") String dictValue, @Param("lang") String lang);
}
