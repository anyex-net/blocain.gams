/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParBrokerInfo;
import com.fintech.gams.account.mapper.AccountParBrokerInfoMapper;

/**
 * 合伙企业经济商信息 服务实现类
 * <p>File：AccountParBrokerInfoServiceImpl.java </p>
 * <p>Title: AccountParBrokerInfoServiceImpl </p>
 * <p>Description:AccountParBrokerInfoServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParBrokerInfoService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParBrokerInfoServiceImpl extends GenericServiceImpl<AccountParBrokerInfo> implements AccountParBrokerInfoService
{

    protected AccountParBrokerInfoMapper accountParBrokerInfoMapper;

    @Autowired(required = false)
    public AccountParBrokerInfoServiceImpl(AccountParBrokerInfoMapper accountParBrokerInfoMapper)
    {
        super(accountParBrokerInfoMapper);
        this.accountParBrokerInfoMapper = accountParBrokerInfoMapper;
    }
}
