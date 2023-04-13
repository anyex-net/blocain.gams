/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoAsset;
import com.fintech.gams.account.mapper.AccountCoAssetMapper;


/**
 * 公司资产信息 服务实现类
 * <p>File：AccountCoAssetServiceImpl.java </p>
 * <p>Title: AccountCoAssetServiceImpl </p>
 * <p>Description:AccountCoAssetServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoAssetServiceImpl extends GenericServiceImpl<AccountCoAsset> implements AccountCoAssetService
{

    protected AccountCoAssetMapper accountCoAssetMapper;

    @Autowired(required = false)
    public AccountCoAssetServiceImpl(AccountCoAssetMapper accountCoAssetMapper)
    {
        super(accountCoAssetMapper);
        this.accountCoAssetMapper = accountCoAssetMapper;
    }



    @Override
    public AccountCoAsset findByAccountId(Long accountId) throws BusinessException {
        return accountCoAssetMapper.findByAccountId(accountId);
    }
}
