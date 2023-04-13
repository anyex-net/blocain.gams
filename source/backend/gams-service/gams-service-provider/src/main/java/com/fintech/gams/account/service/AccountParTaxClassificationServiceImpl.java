/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParTaxClassification;
import com.fintech.gams.account.mapper.AccountParTaxClassificationMapper;

/**
 * 合伙企业税收分类 服务实现类
 * <p>File：AccountParTaxClassificationServiceImpl.java </p>
 * <p>Title: AccountParTaxClassificationServiceImpl </p>
 * <p>Description:AccountParTaxClassificationServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParTaxClassificationService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParTaxClassificationServiceImpl extends GenericServiceImpl<AccountParTaxClassification> implements AccountParTaxClassificationService
{

    protected AccountParTaxClassificationMapper accountParTaxClassificationMapper;

    @Autowired(required = false)
    public AccountParTaxClassificationServiceImpl(AccountParTaxClassificationMapper accountParTaxClassificationMapper)
    {
        super(accountParTaxClassificationMapper);
        this.accountParTaxClassificationMapper = accountParTaxClassificationMapper;
    }
}
