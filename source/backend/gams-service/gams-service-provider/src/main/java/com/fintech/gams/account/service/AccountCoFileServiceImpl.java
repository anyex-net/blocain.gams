/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCoFile;
import com.fintech.gams.account.mapper.AccountCoFileMapper;

/**
 * 公司文件信息 服务实现类
 * <p>File：AccountCoFileServiceImpl.java </p>
 * <p>Title: AccountCoFileServiceImpl </p>
 * <p>Description:AccountCoFileServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoFileServiceImpl extends GenericServiceImpl<AccountCoFile> implements AccountCoFileService
{

    protected AccountCoFileMapper accountCoFileMapper;

    @Autowired(required = false)
    public AccountCoFileServiceImpl(AccountCoFileMapper accountCoFileMapper)
    {
        super(accountCoFileMapper);
        this.accountCoFileMapper = accountCoFileMapper;
    }
}
