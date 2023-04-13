/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParPersonal;
import com.fintech.gams.account.mapper.AccountParPersonalMapper;

/**
 * 合伙企业个人信息 服务实现类
 * <p>File：AccountParPersonalServiceImpl.java </p>
 * <p>Title: AccountParPersonalServiceImpl </p>
 * <p>Description:AccountParPersonalServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParPersonalService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParPersonalServiceImpl extends GenericServiceImpl<AccountParPersonal> implements AccountParPersonalService
{

    protected AccountParPersonalMapper accountParPersonalMapper;

    @Autowired(required = false)
    public AccountParPersonalServiceImpl(AccountParPersonalMapper accountParPersonalMapper)
    {
        super(accountParPersonalMapper);
        this.accountParPersonalMapper = accountParPersonalMapper;
    }
}
