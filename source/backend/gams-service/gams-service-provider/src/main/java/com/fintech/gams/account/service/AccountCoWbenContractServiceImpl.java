/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoWbenContract;
import com.fintech.gams.account.mapper.AccountCoWbenContractMapper;

/**
 * 公司w8ben表 服务实现类
 * <p>File：AccountCoWbenContractServiceImpl.java </p>
 * <p>Title: AccountCoWbenContractServiceImpl </p>
 * <p>Description:AccountCoWbenContractServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoWbenContractServiceImpl extends GenericServiceImpl<AccountCoWbenContract> implements AccountCoWbenContractService
{

    protected AccountCoWbenContractMapper accountCoWbenContractMapper;

    @Autowired(required = false)
    public AccountCoWbenContractServiceImpl(AccountCoWbenContractMapper accountCoWbenContractMapper)
    {
        super(accountCoWbenContractMapper);
        this.accountCoWbenContractMapper = accountCoWbenContractMapper;
    }
}
