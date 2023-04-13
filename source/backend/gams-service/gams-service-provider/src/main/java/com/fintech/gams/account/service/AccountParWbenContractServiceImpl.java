/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParWbenContract;
import com.fintech.gams.account.mapper.AccountParWbenContractMapper;

/**
 * 合伙企业w8ben表 服务实现类
 * <p>File：AccountParWbenContractServiceImpl.java </p>
 * <p>Title: AccountParWbenContractServiceImpl </p>
 * <p>Description:AccountParWbenContractServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParWbenContractService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParWbenContractServiceImpl extends GenericServiceImpl<AccountParWbenContract> implements AccountParWbenContractService
{

    protected AccountParWbenContractMapper accountParWbenContractMapper;

    @Autowired(required = false)
    public AccountParWbenContractServiceImpl(AccountParWbenContractMapper accountParWbenContractMapper)
    {
        super(accountParWbenContractMapper);
        this.accountParWbenContractMapper = accountParWbenContractMapper;
    }
}
