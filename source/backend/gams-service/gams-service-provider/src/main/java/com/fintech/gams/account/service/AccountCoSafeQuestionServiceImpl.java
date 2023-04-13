/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;


import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.gams.account.entity.AccountCoSafeQuestion;
import com.fintech.gams.account.mapper.AccountCoSafeQuestionMapper;

/**
 * 公司安全问题 服务实现类
 * <p>File：AccountCoSafeQuestionServiceImpl.java </p>
 * <p>Title: AccountCoSafeQuestionServiceImpl </p>
 * <p>Description:AccountCoSafeQuestionServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoSafeQuestionServiceImpl extends GenericServiceImpl<AccountCoSafeQuestion> implements AccountCoSafeQuestionService
{

    protected AccountCoSafeQuestionMapper accountCoSafeQuestionMapper;

    @Autowired(required = false)
    public AccountCoSafeQuestionServiceImpl(AccountCoSafeQuestionMapper accountCoSafeQuestionMapper)
    {
        super(accountCoSafeQuestionMapper);
        this.accountCoSafeQuestionMapper = accountCoSafeQuestionMapper;
    }




}
