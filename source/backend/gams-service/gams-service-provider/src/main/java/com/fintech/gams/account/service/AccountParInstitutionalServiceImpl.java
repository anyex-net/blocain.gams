/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParInstitutional;
import com.fintech.gams.account.mapper.AccountParInstitutionalMapper;

/**
 * 合伙企业机构信息 服务实现类
 * <p>File：AccountParInstitutionalServiceImpl.java </p>
 * <p>Title: AccountParInstitutionalServiceImpl </p>
 * <p>Description:AccountParInstitutionalServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParInstitutionalService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParInstitutionalServiceImpl extends GenericServiceImpl<AccountParInstitutional> implements AccountParInstitutionalService
{

    protected AccountParInstitutionalMapper accountParInstitutionalMapper;

    @Autowired(required = false)
    public AccountParInstitutionalServiceImpl(AccountParInstitutionalMapper accountParInstitutionalMapper)
    {
        super(accountParInstitutionalMapper);
        this.accountParInstitutionalMapper = accountParInstitutionalMapper;
    }
}
