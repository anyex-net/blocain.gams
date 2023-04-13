/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.asset.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.asset.entity.AssetWithdraw;
import com.fintech.gams.asset.mapper.AssetWithdrawMapper;
import com.fintech.gams.bean.GenericServiceImpl;

/**
 * 提现出金记录表 服务实现类
 * <p>File：AssetWithdrawServiceImpl.java </p>
 * <p>Title: AssetWithdrawServiceImpl </p>
 * <p>Description:AssetWithdrawServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class AssetWithdrawServiceImpl extends GenericServiceImpl<AssetWithdraw> implements AssetWithdrawService
{
    protected AssetWithdrawMapper assetWithdrawMapper;

    @Autowired(required = false)
    public AssetWithdrawServiceImpl(AssetWithdrawMapper assetWithdrawMapper)
    {
        super(assetWithdrawMapper);
        this.assetWithdrawMapper = assetWithdrawMapper;
    }
}
