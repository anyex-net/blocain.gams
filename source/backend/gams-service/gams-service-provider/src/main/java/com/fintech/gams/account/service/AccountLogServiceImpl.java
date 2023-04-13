/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.account.entity.AccountLog;
import com.fintech.gams.account.mapper.AccountLogMapper;
import com.fintech.gams.bean.GenericServiceImpl;

/**
 * 帐户日志 服务实现类
 * <p>File：AccountLogServiceImpl.java </p>
 * <p>Title: AccountLogServiceImpl </p>
 * <p>Description:AccountLogServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountLogServiceImpl extends GenericServiceImpl<AccountLog> implements AccountLogService
{
    protected AccountLogMapper accountLogMapper;
    
    @Autowired(required = false)
    public AccountLogServiceImpl(AccountLogMapper accountLogMapper)
    {
        super(accountLogMapper);
        this.accountLogMapper = accountLogMapper;
    }
}
