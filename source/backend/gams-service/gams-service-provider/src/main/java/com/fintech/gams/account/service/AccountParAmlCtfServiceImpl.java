/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParAmlCtf;
import com.fintech.gams.account.mapper.AccountParAmlCtfMapper;

/**
 * 合伙企业AML/CTF风险问卷 服务实现类
 * <p>File：AccountParAmlCtfServiceImpl.java </p>
 * <p>Title: AccountParAmlCtfServiceImpl </p>
 * <p>Description:AccountParAmlCtfServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParAmlCtfService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParAmlCtfServiceImpl extends GenericServiceImpl<AccountParAmlCtf> implements AccountParAmlCtfService
{

    protected AccountParAmlCtfMapper accountParAmlCtfMapper;

    @Autowired(required = false)
    public AccountParAmlCtfServiceImpl(AccountParAmlCtfMapper accountParAmlCtfMapper)
    {
        super(accountParAmlCtfMapper);
        this.accountParAmlCtfMapper = accountParAmlCtfMapper;
    }
}
