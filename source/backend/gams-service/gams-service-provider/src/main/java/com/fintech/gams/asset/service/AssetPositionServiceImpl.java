/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.asset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.asset.entity.AssetPosition;
import com.fintech.gams.asset.mapper.AssetPositionMapper;
import com.fintech.gams.bean.GenericServiceImpl;

/**
 * 资产持仓表 服务实现类
 * <p>File：AssetPositionServiceImpl.java </p>
 * <p>Title: AssetPositionServiceImpl </p>
 * <p>Description:AssetPositionServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AssetPositionServiceImpl extends GenericServiceImpl<AssetPosition> implements AssetPositionService
{
    protected AssetPositionMapper assetPositionMapper;

    @Autowired(required = false)
    public AssetPositionServiceImpl(AssetPositionMapper assetPositionMapper)
    {
        super(assetPositionMapper);
        this.assetPositionMapper = assetPositionMapper;
    }
}
