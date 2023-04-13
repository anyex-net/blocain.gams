/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParSecurityOfficer;
import com.fintech.gams.account.mapper.AccountParSecurityOfficerMapper;

/**
 * 合伙企业安全官 服务实现类
 * <p>File：AccountParSecurityOfficerServiceImpl.java </p>
 * <p>Title: AccountParSecurityOfficerServiceImpl </p>
 * <p>Description:AccountParSecurityOfficerServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParSecurityOfficerService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParSecurityOfficerServiceImpl extends GenericServiceImpl<AccountParSecurityOfficer> implements AccountParSecurityOfficerService
{

    protected AccountParSecurityOfficerMapper accountParSecurityOfficerMapper;

    @Autowired(required = false)
    public AccountParSecurityOfficerServiceImpl(AccountParSecurityOfficerMapper accountParSecurityOfficerMapper)
    {
        super(accountParSecurityOfficerMapper);
        this.accountParSecurityOfficerMapper = accountParSecurityOfficerMapper;
    }
}
