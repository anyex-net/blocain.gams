/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParBrokerPermission;
import com.fintech.gams.account.mapper.AccountParBrokerPermissionMapper;

/**
 * 合伙企业经济商监管许可 服务实现类
 * <p>File：AccountParBrokerPermissionServiceImpl.java </p>
 * <p>Title: AccountParBrokerPermissionServiceImpl </p>
 * <p>Description:AccountParBrokerPermissionServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParBrokerPermissionService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParBrokerPermissionServiceImpl extends GenericServiceImpl<AccountParBrokerPermission> implements AccountParBrokerPermissionService
{

    protected AccountParBrokerPermissionMapper accountParBrokerPermissionMapper;

    @Autowired(required = false)
    public AccountParBrokerPermissionServiceImpl(AccountParBrokerPermissionMapper accountParBrokerPermissionMapper)
    {
        super(accountParBrokerPermissionMapper);
        this.accountParBrokerPermissionMapper = accountParBrokerPermissionMapper;
    }
}
