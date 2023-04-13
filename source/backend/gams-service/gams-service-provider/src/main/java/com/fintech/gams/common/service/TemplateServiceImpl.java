/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Template;
import com.fintech.gams.common.mapper.TemplateMapper;

/**
 * 消息模版 服务实现类
 * <p>File：TemplateServiceImpl.java </p>
 * <p>Title: TemplateServiceImpl </p>
 * <p>Description:TemplateServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class TemplateServiceImpl extends GenericServiceImpl<Template> implements TemplateService
{

    protected TemplateMapper templateMapper;

    @Autowired(required = false)
    public TemplateServiceImpl(TemplateMapper templateMapper)
    {
        super(templateMapper);
        this.templateMapper = templateMapper;
    }
}
