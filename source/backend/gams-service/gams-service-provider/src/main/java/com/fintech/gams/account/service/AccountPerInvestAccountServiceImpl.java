/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerInvestAccount;
import com.fintech.gams.account.mapper.AccountPerInvestAccountMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人一体化投资管理账户功能 服务实现类
 * <p>File：AccountPerInvestAccountServiceImpl.java </p>
 * <p>Title: AccountPerInvestAccountServiceImpl </p>
 * <p>Description:AccountPerInvestAccountServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerInvestAccountServiceImpl extends GenericServiceImpl<AccountPerInvestAccount> implements AccountPerInvestAccountService
{

    protected AccountPerInvestAccountMapper accountPerInvestAccountMapper;

    @Autowired(required = false)
    public AccountPerInvestAccountServiceImpl(AccountPerInvestAccountMapper accountPerInvestAccountMapper)
    {
        super(accountPerInvestAccountMapper);
        this.accountPerInvestAccountMapper = accountPerInvestAccountMapper;
    }

}
