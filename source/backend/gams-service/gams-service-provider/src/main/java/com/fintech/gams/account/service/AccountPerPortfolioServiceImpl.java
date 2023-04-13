/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerPortfolio;
import com.fintech.gams.account.mapper.AccountPerPortfolioMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人账户投资组合 服务实现类
 * <p>File：AccountPerPortfolioServiceImpl.java </p>
 * <p>Title: AccountPerPortfolioServiceImpl </p>
 * <p>Description:AccountPerPortfolioServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerPortfolioServiceImpl extends GenericServiceImpl<AccountPerPortfolio> implements AccountPerPortfolioService
{

    protected AccountPerPortfolioMapper accountPerPortfolioMapper;

    @Autowired(required = false)
    public AccountPerPortfolioServiceImpl(AccountPerPortfolioMapper accountPerPortfolioMapper)
    {
        super(accountPerPortfolioMapper);
        this.accountPerPortfolioMapper = accountPerPortfolioMapper;
    }
}
