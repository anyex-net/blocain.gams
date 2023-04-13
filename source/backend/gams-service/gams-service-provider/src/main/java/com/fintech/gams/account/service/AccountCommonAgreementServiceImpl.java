/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountCommonAgreement;
import com.fintech.gams.account.mapper.AccountCommonAgreementMapper;
import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ACCOUNT_COMMON_AGREEMENT 服务实现类
 * <p>File：AccountCommonAgreementServiceImpl.java </p>
 * <p>Title: AccountCommonAgreementServiceImpl </p>
 * <p>Description:AccountCommonAgreementServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountCommonAgreementService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountCommonAgreementServiceImpl extends GenericServiceImpl<AccountCommonAgreement> implements AccountCommonAgreementService
{

    protected AccountCommonAgreementMapper accountCommonAgreementMapper;

    @Autowired(required = false)
    public AccountCommonAgreementServiceImpl(AccountCommonAgreementMapper accountCommonAgreementMapper)
    {
        super(accountCommonAgreementMapper);
        this.accountCommonAgreementMapper = accountCommonAgreementMapper;
    }
}
