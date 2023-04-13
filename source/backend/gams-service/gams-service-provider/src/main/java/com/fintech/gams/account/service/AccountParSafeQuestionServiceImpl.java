/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParSafeQuestion;
import com.fintech.gams.account.mapper.AccountParSafeQuestionMapper;

/**
 * 合伙企业安全问题 服务实现类
 * <p>File：AccountParSafeQuestionServiceImpl.java </p>
 * <p>Title: AccountParSafeQuestionServiceImpl </p>
 * <p>Description:AccountParSafeQuestionServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParSafeQuestionService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParSafeQuestionServiceImpl extends GenericServiceImpl<AccountParSafeQuestion> implements AccountParSafeQuestionService
{

    protected AccountParSafeQuestionMapper accountParSafeQuestionMapper;

    @Autowired(required = false)
    public AccountParSafeQuestionServiceImpl(AccountParSafeQuestionMapper accountParSafeQuestionMapper)
    {
        super(accountParSafeQuestionMapper);
        this.accountParSafeQuestionMapper = accountParSafeQuestionMapper;
    }
}
