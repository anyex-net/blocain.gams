/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.common.entity.Template;

/**
 * 消息模版 持久层接口
 * <p>File：TemplateMapper.java </p>
 * <p>Title: TemplateMapper </p>
 * <p>Description:TemplateMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface TemplateMapper extends GenericMapper<Template>
{
    /**
     * 根据模版编码，类型，语言查询
     * @param tplKey
     * @param type
     * @param lang
     * @return
     */
    Template findByKeyAndLang(@Param("tplKey") String tplKey, @Param("type") String type, @Param("lang") String lang);
}
