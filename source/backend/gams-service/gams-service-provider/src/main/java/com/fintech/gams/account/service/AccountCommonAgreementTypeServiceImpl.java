/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountCommonAgreementType;
import com.fintech.gams.account.mapper.AccountCommonAgreementTypeMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ACCOUNT_COMMON_AGREEMENT_TYPE 服务实现类
 * <p>File：AccountCommonAgreementTypeServiceImpl.java </p>
 * <p>Title: AccountCommonAgreementTypeServiceImpl </p>
 * <p>Description:AccountCommonAgreementTypeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountCommonAgreementTypeService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountCommonAgreementTypeServiceImpl extends GenericServiceImpl<AccountCommonAgreementType> implements AccountCommonAgreementTypeService
{

    protected AccountCommonAgreementTypeMapper accountCommonAgreementTypeMapper;

    @Autowired(required = false)
    public AccountCommonAgreementTypeServiceImpl(AccountCommonAgreementTypeMapper accountCommonAgreementTypeMapper)
    {
        super(accountCommonAgreementTypeMapper);
        this.accountCommonAgreementTypeMapper = accountCommonAgreementTypeMapper;
    }
}
