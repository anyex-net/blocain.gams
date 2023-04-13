/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.common.entity.Express;
import com.fintech.gams.common.model.ThumbsUpModel;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 快讯信息 服务接口
 * <p>File：ExpressService.java </p>
 * <p>Title: ExpressService </p>
 * <p>Description:ExpressService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface ExpressService extends GenericService<Express>
{
    /**
     * 查看详情
     * @param id
     * @return
     * @throws BusinessException
     */
    Express detail(Long id) throws BusinessException;

    /**
     * 点赞或取消点赞快讯信息
     *
     * @param ip
     * @param model
     * @throws BusinessException
     */
    void thumbsUpOrCancel(String ip, ThumbsUpModel model) throws BusinessException;

    /**
     * 查询热门阅读的快讯信息
     *
     * @param lang
     * @return
     * @throws BusinessException
     */
    List<Express> hotReading(String lang) throws BusinessException;
}
