/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.asset.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.asset.entity.AssetDeposit;
import com.fintech.gams.asset.mapper.AssetDepositMapper;
import com.fintech.gams.bean.GenericServiceImpl;


/**
 * 充值入金记录表 服务实现类
 * <p>File：AssetDepositServiceImpl.java </p>
 * <p>Title: AssetDepositServiceImpl </p>
 * <p>Description:AssetDepositServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class AssetDepositServiceImpl extends GenericServiceImpl<AssetDeposit> implements AssetDepositService
{
    protected AssetDepositMapper assetDepositMapper;
    
    @Autowired(required = false)
    public AssetDepositServiceImpl(AssetDepositMapper assetDepositMapper)
    {
        super(assetDepositMapper);
        this.assetDepositMapper = assetDepositMapper;
    }
}
