/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoTaxClassification;
import com.fintech.gams.account.mapper.AccountCoTaxClassificationMapper;


/**
 * 公司税收分类 服务实现类
 * <p>File：AccountCoTaxClassificationServiceImpl.java </p>
 * <p>Title: AccountCoTaxClassificationServiceImpl </p>
 * <p>Description:AccountCoTaxClassificationServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoTaxClassificationServiceImpl extends GenericServiceImpl<AccountCoTaxClassification> implements AccountCoTaxClassificationService
{

    protected AccountCoTaxClassificationMapper accountCoTaxClassificationMapper;

    @Autowired(required = false)
    public AccountCoTaxClassificationServiceImpl(AccountCoTaxClassificationMapper accountCoTaxClassificationMapper)
    {
        super(accountCoTaxClassificationMapper);
        this.accountCoTaxClassificationMapper = accountCoTaxClassificationMapper;
    }

    @Override
    public AccountCoTaxClassification findByAccountId(Long accountId) throws BusinessException {
        return accountCoTaxClassificationMapper.findByAccountId(accountId);
    }
}
