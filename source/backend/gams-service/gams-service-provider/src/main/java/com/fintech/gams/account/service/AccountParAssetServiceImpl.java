/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParAsset;
import com.fintech.gams.account.mapper.AccountParAssetMapper;

/**
 * 合伙企业资产信息 服务实现类
 * <p>File：AccountParAssetServiceImpl.java </p>
 * <p>Title: AccountParAssetServiceImpl </p>
 * <p>Description:AccountParAssetServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParAssetService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParAssetServiceImpl extends GenericServiceImpl<AccountParAsset> implements AccountParAssetService
{

    protected AccountParAssetMapper accountParAssetMapper;

    @Autowired(required = false)
    public AccountParAssetServiceImpl(AccountParAssetMapper accountParAssetMapper)
    {
        super(accountParAssetMapper);
        this.accountParAssetMapper = accountParAssetMapper;
    }
}
