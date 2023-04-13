/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParFile;
import com.fintech.gams.account.mapper.AccountParFileMapper;

/**
 * 合伙企业文件信息 服务实现类
 * <p>File：AccountParFileServiceImpl.java </p>
 * <p>Title: AccountParFileServiceImpl </p>
 * <p>Description:AccountParFileServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParFileService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParFileServiceImpl extends GenericServiceImpl<AccountParFile> implements AccountParFileService
{

    protected AccountParFileMapper accountParFileMapper;

    @Autowired(required = false)
    public AccountParFileServiceImpl(AccountParFileMapper accountParFileMapper)
    {
        super(accountParFileMapper);
        this.accountParFileMapper = accountParFileMapper;
    }
}
