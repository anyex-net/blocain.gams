/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;


import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.gams.account.entity.AccountCoRegulatory;
import com.fintech.gams.account.mapper.AccountCoRegulatoryMapper;


/**
 * 公司规管信息 服务实现类
 * <p>File：AccountCoRegulatoryServiceImpl.java </p>
 * <p>Title: AccountCoRegulatoryServiceImpl </p>
 * <p>Description:AccountCoRegulatoryServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoRegulatoryServiceImpl extends GenericServiceImpl<AccountCoRegulatory> implements AccountCoRegulatoryService
{

    protected AccountCoRegulatoryMapper accountCoRegulatoryMapper;

    @Autowired(required = false)
    public AccountCoRegulatoryServiceImpl(AccountCoRegulatoryMapper accountCoRegulatoryMapper)
    {
        super(accountCoRegulatoryMapper);
        this.accountCoRegulatoryMapper = accountCoRegulatoryMapper;
    }



}
