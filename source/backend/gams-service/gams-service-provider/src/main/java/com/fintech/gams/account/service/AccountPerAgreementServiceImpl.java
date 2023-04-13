/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerAgreement;
import com.fintech.gams.account.mapper.AccountPerAgreementMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人协议纰漏 服务实现类
 * <p>File：AccountPerAgreementServiceImpl.java </p>
 * <p>Title: AccountPerAgreementServiceImpl </p>
 * <p>Description:AccountPerAgreementServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerAgreementServiceImpl extends GenericServiceImpl<AccountPerAgreement> implements AccountPerAgreementService
{

    protected AccountPerAgreementMapper accountPerAgreementMapper;

    @Autowired(required = false)
    public AccountPerAgreementServiceImpl(AccountPerAgreementMapper accountPerAgreementMapper)
    {
        super(accountPerAgreementMapper);
        this.accountPerAgreementMapper = accountPerAgreementMapper;
    }

}
