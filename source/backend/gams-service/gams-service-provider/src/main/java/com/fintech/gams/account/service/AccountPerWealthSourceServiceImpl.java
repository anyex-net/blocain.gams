/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerWealthSource;
import com.fintech.gams.account.mapper.AccountPerWealthSourceMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人财富来源 服务实现类
 * <p>File：AccountPerWealthSourceServiceImpl.java </p>
 * <p>Title: AccountPerWealthSourceServiceImpl </p>
 * <p>Description:AccountPerWealthSourceServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerWealthSourceServiceImpl extends GenericServiceImpl<AccountPerWealthSource> implements AccountPerWealthSourceService
{

    protected AccountPerWealthSourceMapper accountPerWealthSourceMapper;

    @Autowired(required = false)
    public AccountPerWealthSourceServiceImpl(AccountPerWealthSourceMapper accountPerWealthSourceMapper)
    {
        super(accountPerWealthSourceMapper);
        this.accountPerWealthSourceMapper = accountPerWealthSourceMapper;
    }
}
