/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoCrsEntity;
import com.fintech.gams.account.mapper.AccountCoCrsEntityMapper;

/**
 * 公司CRS实体自我证明表格 服务实现类
 * <p>File：AccountCoCrsEntityServiceImpl.java </p>
 * <p>Title: AccountCoCrsEntityServiceImpl </p>
 * <p>Description:AccountCoCrsEntityServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountCoCrsEntityService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountCoCrsEntityServiceImpl extends GenericServiceImpl<AccountCoCrsEntity> implements AccountCoCrsEntityService
{

    protected AccountCoCrsEntityMapper accountCoCrsEntityMapper;

    @Autowired(required = false)
    public AccountCoCrsEntityServiceImpl(AccountCoCrsEntityMapper accountCoCrsEntityMapper)
    {
        super(accountCoCrsEntityMapper);
        this.accountCoCrsEntityMapper = accountCoCrsEntityMapper;
    }
}
