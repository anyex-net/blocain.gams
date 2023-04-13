/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerAsset;
import com.fintech.gams.account.mapper.AccountPerAssetMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人资产信息 服务实现类
 * <p>File：AccountPerAssetServiceImpl.java </p>
 * <p>Title: AccountPerAssetServiceImpl </p>
 * <p>Description:AccountPerAssetServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerAssetServiceImpl extends GenericServiceImpl<AccountPerAsset> implements AccountPerAssetService
{

    protected AccountPerAssetMapper accountPerAssetMapper;

    @Autowired(required = false)
    public AccountPerAssetServiceImpl(AccountPerAssetMapper accountPerAssetMapper)
    {
        super(accountPerAssetMapper);
        this.accountPerAssetMapper = accountPerAssetMapper;
    }


}
