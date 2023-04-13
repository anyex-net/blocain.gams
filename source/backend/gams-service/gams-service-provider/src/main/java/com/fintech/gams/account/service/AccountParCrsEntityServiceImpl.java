/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParCrsEntity;
import com.fintech.gams.account.mapper.AccountParCrsEntityMapper;

/**
 * 合伙企业CRS实体自我证明表格(CRS-CP) 服务实现类
 * <p>File：AccountParCrsEntityServiceImpl.java </p>
 * <p>Title: AccountParCrsEntityServiceImpl </p>
 * <p>Description:AccountParCrsEntityServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParCrsEntityService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParCrsEntityServiceImpl extends GenericServiceImpl<AccountParCrsEntity> implements AccountParCrsEntityService
{

    protected AccountParCrsEntityMapper accountParCrsEntityMapper;

    @Autowired(required = false)
    public AccountParCrsEntityServiceImpl(AccountParCrsEntityMapper accountParCrsEntityMapper)
    {
        super(accountParCrsEntityMapper);
        this.accountParCrsEntityMapper = accountParCrsEntityMapper;
    }
}
