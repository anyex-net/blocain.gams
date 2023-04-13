/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoCrsEntityContr;
import com.fintech.gams.account.mapper.AccountCoCrsEntityContrMapper;

import java.util.List;

/**
 * 公司crs实体（CRS-E）控权人信息 服务实现类
 * <p>File：AccountCoCrsEntityContrServiceImpl.java </p>
 * <p>Title: AccountCoCrsEntityContrServiceImpl </p>
 * <p>Description:AccountCoCrsEntityContrServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountCoCrsEntityContrService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountCoCrsEntityContrServiceImpl extends GenericServiceImpl<AccountCoCrsEntityContr> implements AccountCoCrsEntityContrService
{

    protected AccountCoCrsEntityContrMapper accountCoCrsEntityContrMapper;

    @Autowired(required = false)
    public AccountCoCrsEntityContrServiceImpl(AccountCoCrsEntityContrMapper accountCoCrsEntityContrMapper)
    {
        super(accountCoCrsEntityContrMapper);
        this.accountCoCrsEntityContrMapper = accountCoCrsEntityContrMapper;
    }

}
