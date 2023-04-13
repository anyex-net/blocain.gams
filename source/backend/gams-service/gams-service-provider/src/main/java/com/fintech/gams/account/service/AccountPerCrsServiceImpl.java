/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountPerCrs;
import com.fintech.gams.account.mapper.AccountPerCrsMapper;

/**
 * 个人CRS 服务实现类
 * <p>File：AccountPerCrsServiceImpl.java </p>
 * <p>Title: AccountPerCrsServiceImpl </p>
 * <p>Description:AccountPerCrsServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerCrsServiceImpl extends GenericServiceImpl<AccountPerCrs> implements AccountPerCrsService
{

    protected AccountPerCrsMapper accountPerCrsMapper;

    @Autowired(required = false)
    public AccountPerCrsServiceImpl(AccountPerCrsMapper accountPerCrsMapper)
    {
        super(accountPerCrsMapper);
        this.accountPerCrsMapper = accountPerCrsMapper;
    }
}
