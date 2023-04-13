/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.SerialnoUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoAccountPortfolio;
import com.fintech.gams.account.mapper.AccountCoAccountPortfolioMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * 公司账户组合信息 服务实现类
 * <p>File：AccountCoAccountPortfolioServiceImpl.java </p>
 * <p>Title: AccountCoAccountPortfolioServiceImpl </p>
 * <p>Description:AccountCoAccountPortfolioServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoAccountPortfolioServiceImpl extends GenericServiceImpl<AccountCoAccountPortfolio> implements AccountCoAccountPortfolioService
{

    protected AccountCoAccountPortfolioMapper accountCoAccountPortfolioMapper;

    @Autowired(required = false)
    public AccountCoAccountPortfolioServiceImpl(AccountCoAccountPortfolioMapper accountCoAccountPortfolioMapper)
    {
        super(accountCoAccountPortfolioMapper);
        this.accountCoAccountPortfolioMapper = accountCoAccountPortfolioMapper;
    }



    @Override
    public AccountCoAccountPortfolio findByAccountId(Long accountId) throws BusinessException {
        return accountCoAccountPortfolioMapper.findByAccountId(accountId);
    }


}
