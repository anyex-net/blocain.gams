/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoIdentity;
import com.fintech.gams.account.mapper.AccountCoIdentityMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 公司身份信息 服务实现类
 * <p>File：AccountCoIdentityServiceImpl.java </p>
 * <p>Title: AccountCoIdentityServiceImpl </p>
 * <p>Description:AccountCoIdentityServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoIdentityServiceImpl extends GenericServiceImpl<AccountCoIdentity> implements AccountCoIdentityService
{

    protected AccountCoIdentityMapper accountCoIdentityMapper;

    @Autowired(required = false)
    public AccountCoIdentityServiceImpl(AccountCoIdentityMapper accountCoIdentityMapper)
    {
        super(accountCoIdentityMapper);
        this.accountCoIdentityMapper = accountCoIdentityMapper;
    }



    @Override
    public AccountCoIdentity findByAccountId(Long accountId) throws BusinessException {
        return accountCoIdentityMapper.findByAccountId(accountId);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByCoPersonalId(Long coPersonalId) throws BusinessException {
        return accountCoIdentityMapper.removeByCoPersonalId(coPersonalId);
    }
}
