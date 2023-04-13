/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParIdentity;
import com.fintech.gams.account.mapper.AccountParIdentityMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 合伙企业身份信息 服务实现类
 * <p>File：AccountParIdentityServiceImpl.java </p>
 * <p>Title: AccountParIdentityServiceImpl </p>
 * <p>Description:AccountParIdentityServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParIdentityService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParIdentityServiceImpl extends GenericServiceImpl<AccountParIdentity> implements AccountParIdentityService
{

    protected AccountParIdentityMapper accountParIdentityMapper;

    @Autowired(required = false)
    public AccountParIdentityServiceImpl(AccountParIdentityMapper accountParIdentityMapper)
    {
        super(accountParIdentityMapper);
        this.accountParIdentityMapper = accountParIdentityMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByParPersonalId(Long parPersonalId) throws BusinessException {
        return accountParIdentityMapper.removeByParPersonalId(parPersonalId);
    }
}
