/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.gams.account.entity.AccountCoPersonal;
import com.fintech.gams.account.mapper.AccountCoPersonalMapper;


/**
 * 公司个人信息 服务实现类
 * <p>File：AccountCoPersonalServiceImpl.java </p>
 * <p>Title: AccountCoPersonalServiceImpl </p>
 * <p>Description:AccountCoPersonalServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoPersonalServiceImpl extends GenericServiceImpl<AccountCoPersonal> implements AccountCoPersonalService
{

    protected AccountCoPersonalMapper accountCoPersonalMapper;

    @Autowired(required = false)
    public AccountCoPersonalServiceImpl(AccountCoPersonalMapper accountCoPersonalMapper)
    {
        super(accountCoPersonalMapper);
        this.accountCoPersonalMapper = accountCoPersonalMapper;
    }



}
