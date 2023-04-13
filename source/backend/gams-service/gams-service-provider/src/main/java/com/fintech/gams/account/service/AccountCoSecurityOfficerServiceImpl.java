/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoSecurityOfficer;
import com.fintech.gams.account.mapper.AccountCoSecurityOfficerMapper;

/**
 * 公司安全官 服务实现类
 * <p>File：AccountCoSecurityOfficerServiceImpl.java </p>
 * <p>Title: AccountCoSecurityOfficerServiceImpl </p>
 * <p>Description:AccountCoSecurityOfficerServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoSecurityOfficerServiceImpl extends GenericServiceImpl<AccountCoSecurityOfficer> implements AccountCoSecurityOfficerService
{

    protected AccountCoSecurityOfficerMapper accountCoSecurityOfficerMapper;

    @Autowired(required = false)
    public AccountCoSecurityOfficerServiceImpl(AccountCoSecurityOfficerMapper accountCoSecurityOfficerMapper)
    {
        super(accountCoSecurityOfficerMapper);
        this.accountCoSecurityOfficerMapper = accountCoSecurityOfficerMapper;
    }
}
