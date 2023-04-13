/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.common.entity.Parameter;

/**
 * 参数表 持久层接口
 * <p>File：ParameterMapper.java </p>
 * <p>Title: ParameterMapper </p>
 * <p>Description:ParameterMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface ParameterMapper extends GenericMapper<Parameter>
{

}
