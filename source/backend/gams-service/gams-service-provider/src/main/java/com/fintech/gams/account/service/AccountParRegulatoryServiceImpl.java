/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParRegulatory;
import com.fintech.gams.account.mapper.AccountParRegulatoryMapper;

/**
 * 合伙企业规管信息 服务实现类
 * <p>File：AccountParRegulatoryServiceImpl.java </p>
 * <p>Title: AccountParRegulatoryServiceImpl </p>
 * <p>Description:AccountParRegulatoryServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParRegulatoryService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParRegulatoryServiceImpl extends GenericServiceImpl<AccountParRegulatory> implements AccountParRegulatoryService
{

    protected AccountParRegulatoryMapper accountParRegulatoryMapper;

    @Autowired(required = false)
    public AccountParRegulatoryServiceImpl(AccountParRegulatoryMapper accountParRegulatoryMapper)
    {
        super(accountParRegulatoryMapper);
        this.accountParRegulatoryMapper = accountParRegulatoryMapper;
    }
}
