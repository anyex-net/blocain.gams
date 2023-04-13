/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoAgreement;
import com.fintech.gams.account.mapper.AccountCoAgreementMapper;

/**
 * 公司协议纰漏信息 服务实现类
 * <p>File：AccountCoAgreementServiceImpl.java </p>
 * <p>Title: AccountCoAgreementServiceImpl </p>
 * <p>Description:AccountCoAgreementServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoAgreementServiceImpl extends GenericServiceImpl<AccountCoAgreement> implements AccountCoAgreementService
{

    protected AccountCoAgreementMapper accountCoAgreementMapper;

    @Autowired(required = false)
    public AccountCoAgreementServiceImpl(AccountCoAgreementMapper accountCoAgreementMapper)
    {
        super(accountCoAgreementMapper);
        this.accountCoAgreementMapper = accountCoAgreementMapper;
    }
}
