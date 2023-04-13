/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParInvestmentAccount;
import com.fintech.gams.account.mapper.AccountParInvestmentAccountMapper;

/**
 * 合伙企业一体化投资管理账户 服务实现类
 * <p>File：AccountParInvestmentAccountServiceImpl.java </p>
 * <p>Title: AccountParInvestmentAccountServiceImpl </p>
 * <p>Description:AccountParInvestmentAccountServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParInvestmentAccountService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParInvestmentAccountServiceImpl extends GenericServiceImpl<AccountParInvestmentAccount> implements AccountParInvestmentAccountService
{

    protected AccountParInvestmentAccountMapper accountParInvestmentAccountMapper;

    @Autowired(required = false)
    public AccountParInvestmentAccountServiceImpl(AccountParInvestmentAccountMapper accountParInvestmentAccountMapper)
    {
        super(accountParInvestmentAccountMapper);
        this.accountParInvestmentAccountMapper = accountParInvestmentAccountMapper;
    }
}
