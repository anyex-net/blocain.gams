/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoInstitutional;
import com.fintech.gams.account.mapper.AccountCoInstitutionalMapper;


/**
 * 公司机构 服务实现类
 * <p>File：AccountCoInstitutionalServiceImpl.java </p>
 * <p>Title: AccountCoInstitutionalServiceImpl </p>
 * <p>Description:AccountCoInstitutionalServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoInstitutionalServiceImpl extends GenericServiceImpl<AccountCoInstitutional> implements AccountCoInstitutionalService
{

    protected AccountCoInstitutionalMapper accountCoInstitutionalMapper;

    @Autowired(required = false)
    public AccountCoInstitutionalServiceImpl(AccountCoInstitutionalMapper accountCoInstitutionalMapper)
    {
        super(accountCoInstitutionalMapper);
        this.accountCoInstitutionalMapper = accountCoInstitutionalMapper;
    }



    @Override
    public AccountCoInstitutional findByAccountId(Long accountId) throws BusinessException {
        return accountCoInstitutionalMapper.findByAccountId(accountId);
    }


}
