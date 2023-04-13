/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoAmlCtf;
import com.fintech.gams.account.mapper.AccountCoAmlCtfMapper;

/**
 * 公司AML/CTF风险问卷 服务实现类
 * <p>File：AccountCoAmlCtfServiceImpl.java </p>
 * <p>Title: AccountCoAmlCtfServiceImpl </p>
 * <p>Description:AccountCoAmlCtfServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountCoAmlCtfService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountCoAmlCtfServiceImpl extends GenericServiceImpl<AccountCoAmlCtf> implements AccountCoAmlCtfService
{

    protected AccountCoAmlCtfMapper accountCoAmlCtfMapper;

    @Autowired(required = false)
    public AccountCoAmlCtfServiceImpl(AccountCoAmlCtfMapper accountCoAmlCtfMapper)
    {
        super(accountCoAmlCtfMapper);
        this.accountCoAmlCtfMapper = accountCoAmlCtfMapper;
    }
}
