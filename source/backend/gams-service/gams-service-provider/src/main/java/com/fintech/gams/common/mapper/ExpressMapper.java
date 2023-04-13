/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.common.entity.Express;
import com.fintech.gams.exception.BusinessException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 快讯信息 持久层接口
 * <p>File：ExpressMapper.java </p>
 * <p>Title: ExpressMapper </p>
 * <p>Description:ExpressMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface ExpressMapper extends GenericMapper<Express>
{

    /**
     * 查看详情
     * @param id
     * @return
     */
    Express detail(Long id);

    /**
     *  查询热门阅读的快讯信息
     * @param lang
     * @param time
     * @return
     */
    List<Express> hotReading(@Param("lang") String lang, @Param("time") Long time);
}
