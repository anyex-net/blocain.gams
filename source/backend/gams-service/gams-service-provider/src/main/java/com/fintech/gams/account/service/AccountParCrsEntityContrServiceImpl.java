/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParCrsEntityContr;
import com.fintech.gams.account.mapper.AccountParCrsEntityContrMapper;

/**
 * 合伙企业crs实体（CRS-E）控权人信息 服务实现类
 * <p>File：AccountParCrsEntityContrServiceImpl.java </p>
 * <p>Title: AccountParCrsEntityContrServiceImpl </p>
 * <p>Description:AccountParCrsEntityContrServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParCrsEntityContrService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParCrsEntityContrServiceImpl extends GenericServiceImpl<AccountParCrsEntityContr> implements AccountParCrsEntityContrService
{

    protected AccountParCrsEntityContrMapper accountParCrsEntityContrMapper;

    @Autowired(required = false)
    public AccountParCrsEntityContrServiceImpl(AccountParCrsEntityContrMapper accountParCrsEntityContrMapper)
    {
        super(accountParCrsEntityContrMapper);
        this.accountParCrsEntityContrMapper = accountParCrsEntityContrMapper;
    }
}
