/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParAgreement;
import com.fintech.gams.account.mapper.AccountParAgreementMapper;

/**
 * 合伙企业协议纰漏信息 服务实现类
 * <p>File：AccountParAgreementServiceImpl.java </p>
 * <p>Title: AccountParAgreementServiceImpl </p>
 * <p>Description:AccountParAgreementServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParAgreementService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParAgreementServiceImpl extends GenericServiceImpl<AccountParAgreement> implements AccountParAgreementService
{

    protected AccountParAgreementMapper accountParAgreementMapper;

    @Autowired(required = false)
    public AccountParAgreementServiceImpl(AccountParAgreementMapper accountParAgreementMapper)
    {
        super(accountParAgreementMapper);
        this.accountParAgreementMapper = accountParAgreementMapper;
    }
}
