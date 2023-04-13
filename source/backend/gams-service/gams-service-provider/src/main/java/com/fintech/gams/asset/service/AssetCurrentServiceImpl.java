/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.asset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.asset.entity.AssetCurrent;
import com.fintech.gams.asset.mapper.AssetCurrentMapper;
import com.fintech.gams.bean.GenericServiceImpl;

/**
 * 资产流水表 服务实现类
 * <p>File：AssetCurrentServiceImpl.java </p>
 * <p>Title: AssetCurrentServiceImpl </p>
 * <p>Description:AssetCurrentServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AssetCurrentServiceImpl extends GenericServiceImpl<AssetCurrent> implements AssetCurrentService
{

    protected AssetCurrentMapper assetCurrentMapper;

    @Autowired(required = false)
    public AssetCurrentServiceImpl(AssetCurrentMapper assetCurrentMapper)
    {
        super(assetCurrentMapper);
        this.assetCurrentMapper = assetCurrentMapper;
    }
}
