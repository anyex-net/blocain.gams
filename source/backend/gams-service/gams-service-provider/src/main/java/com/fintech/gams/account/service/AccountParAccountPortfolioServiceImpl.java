/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParAccountPortfolio;
import com.fintech.gams.account.mapper.AccountParAccountPortfolioMapper;

/**
 * 合伙企业账户组合信息 服务实现类
 * <p>File：AccountParAccountPortfolioServiceImpl.java </p>
 * <p>Title: AccountParAccountPortfolioServiceImpl </p>
 * <p>Description:AccountParAccountPortfolioServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParAccountPortfolioService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParAccountPortfolioServiceImpl extends GenericServiceImpl<AccountParAccountPortfolio> implements AccountParAccountPortfolioService
{

    protected AccountParAccountPortfolioMapper accountParAccountPortfolioMapper;

    @Autowired(required = false)
    public AccountParAccountPortfolioServiceImpl(AccountParAccountPortfolioMapper accountParAccountPortfolioMapper)
    {
        super(accountParAccountPortfolioMapper);
        this.accountParAccountPortfolioMapper = accountParAccountPortfolioMapper;
    }
}
