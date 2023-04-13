/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountPerAmlCtf;
import com.fintech.gams.account.mapper.AccountPerAmlCtfMapper;

/**
 * 个人AML/CTF风险问卷 服务实现类
 * <p>File：AccountPerAmlCtfServiceImpl.java </p>
 * <p>Title: AccountPerAmlCtfServiceImpl </p>
 * <p>Description:AccountPerAmlCtfServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountPerAmlCtfService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountPerAmlCtfServiceImpl extends GenericServiceImpl<AccountPerAmlCtf> implements AccountPerAmlCtfService
{

    protected AccountPerAmlCtfMapper accountPerAmlCtfMapper;

    @Autowired(required = false)
    public AccountPerAmlCtfServiceImpl(AccountPerAmlCtfMapper accountPerAmlCtfMapper)
    {
        super(accountPerAmlCtfMapper);
        this.accountPerAmlCtfMapper = accountPerAmlCtfMapper;
    }
}
