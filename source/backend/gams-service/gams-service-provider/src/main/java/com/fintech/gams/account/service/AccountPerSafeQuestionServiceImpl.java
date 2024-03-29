/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerSafeQuestion;
import com.fintech.gams.account.mapper.AccountPerSafeQuestionMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人安全问题 服务实现类
 * <p>File：AccountPerSafeQuestionServiceImpl.java </p>
 * <p>Title: AccountPerSafeQuestionServiceImpl </p>
 * <p>Description:AccountPerSafeQuestionServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerSafeQuestionServiceImpl extends GenericServiceImpl<AccountPerSafeQuestion> implements AccountPerSafeQuestionService
{

    protected AccountPerSafeQuestionMapper accountPerSafeQuestionMapper;

    @Autowired(required = false)
    public AccountPerSafeQuestionServiceImpl(AccountPerSafeQuestionMapper accountPerSafeQuestionMapper)
    {
        super(accountPerSafeQuestionMapper);
        this.accountPerSafeQuestionMapper = accountPerSafeQuestionMapper;
    }
}
