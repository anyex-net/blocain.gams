/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerRegulatory;
import com.fintech.gams.account.mapper.AccountPerRegulatoryMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人账户规管信息 服务实现类
 * <p>File：AccountPerRegulatoryServiceImpl.java </p>
 * <p>Title: AccountPerRegulatoryServiceImpl </p>
 * <p>Description:AccountPerRegulatoryServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerRegulatoryServiceImpl extends GenericServiceImpl<AccountPerRegulatory> implements AccountPerRegulatoryService
{

    protected AccountPerRegulatoryMapper accountPerRegulatoryMapper;

    @Autowired(required = false)
    public AccountPerRegulatoryServiceImpl(AccountPerRegulatoryMapper accountPerRegulatoryMapper)
    {
        super(accountPerRegulatoryMapper);
        this.accountPerRegulatoryMapper = accountPerRegulatoryMapper;
    }
}
