/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;


import com.fintech.gams.account.entity.AccountPerEmployment;
import com.fintech.gams.account.mapper.AccountPerEmploymentMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人雇佣(就业)信息 服务实现类
 * <p>File：AccountPerEmploymentServiceImpl.java </p>
 * <p>Title: AccountPerEmploymentServiceImpl </p>
 * <p>Description:AccountPerEmploymentServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerEmploymentServiceImpl extends GenericServiceImpl<AccountPerEmployment> implements AccountPerEmploymentService
{

    protected AccountPerEmploymentMapper accountPerEmploymentMapper;

    @Autowired(required = false)
    public AccountPerEmploymentServiceImpl(AccountPerEmploymentMapper accountPerEmploymentMapper)
    {
        super(accountPerEmploymentMapper);
        this.accountPerEmploymentMapper = accountPerEmploymentMapper;
    }


}
