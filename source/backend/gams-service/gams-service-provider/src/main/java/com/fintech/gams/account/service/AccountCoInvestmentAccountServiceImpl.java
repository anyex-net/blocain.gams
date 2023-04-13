/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoInvestmentAccount;
import com.fintech.gams.account.mapper.AccountCoInvestmentAccountMapper;

/**
 * 公司一体化投资管理账户 服务实现类
 * <p>File：AccountCoInvestmentAccountServiceImpl.java </p>
 * <p>Title: AccountCoInvestmentAccountServiceImpl </p>
 * <p>Description:AccountCoInvestmentAccountServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoInvestmentAccountServiceImpl extends GenericServiceImpl<AccountCoInvestmentAccount> implements AccountCoInvestmentAccountService
{

    protected AccountCoInvestmentAccountMapper accountCoInvestmentAccountMapper;

    @Autowired(required = false)
    public AccountCoInvestmentAccountServiceImpl(AccountCoInvestmentAccountMapper accountCoInvestmentAccountMapper)
    {
        super(accountCoInvestmentAccountMapper);
        this.accountCoInvestmentAccountMapper = accountCoInvestmentAccountMapper;
    }
}
