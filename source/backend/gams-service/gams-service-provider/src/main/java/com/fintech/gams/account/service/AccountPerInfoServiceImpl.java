/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerInfo;
import com.fintech.gams.account.mapper.AccountPerInfoMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人信息 服务实现类
 * <p>File：AccountPerInfoServiceImpl.java </p>
 * <p>Title: AccountPerInfoServiceImpl </p>
 * <p>Description:AccountPerInfoServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerInfoServiceImpl extends GenericServiceImpl<AccountPerInfo> implements AccountPerInfoService
{

    protected AccountPerInfoMapper accountPerInfoMapper;

    @Autowired(required = false)
    public AccountPerInfoServiceImpl(AccountPerInfoMapper accountPerInfoMapper)
    {
        super(accountPerInfoMapper);
        this.accountPerInfoMapper = accountPerInfoMapper;
    }

}
