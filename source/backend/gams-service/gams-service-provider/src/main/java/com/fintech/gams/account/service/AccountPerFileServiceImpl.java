/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountPerFile;
import com.fintech.gams.account.mapper.AccountPerFileMapper;

/**
 * 个人文件 服务实现类
 * <p>File：AccountPerFileServiceImpl.java </p>
 * <p>Title: AccountPerFileServiceImpl </p>
 * <p>Description:AccountPerFileServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerFileServiceImpl extends GenericServiceImpl<AccountPerFile> implements AccountPerFileService
{

    protected AccountPerFileMapper accountPerFileMapper;

    @Autowired(required = false)
    public AccountPerFileServiceImpl(AccountPerFileMapper accountPerFileMapper)
    {
        super(accountPerFileMapper);
        this.accountPerFileMapper = accountPerFileMapper;
    }
}
