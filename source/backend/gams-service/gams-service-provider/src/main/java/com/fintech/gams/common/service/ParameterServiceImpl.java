/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Parameter;
import com.fintech.gams.common.mapper.ParameterMapper;

/**
 * 参数表 服务实现类
 * <p>File：ParameterServiceImpl.java </p>
 * <p>Title: ParameterServiceImpl </p>
 * <p>Description:ParameterServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ParameterServiceImpl extends GenericServiceImpl<Parameter> implements ParameterService
{

    protected ParameterMapper parameterMapper;

    @Autowired(required = false)
    public ParameterServiceImpl(ParameterMapper parameterMapper)
    {
        super(parameterMapper);
        this.parameterMapper = parameterMapper;
    }
}
