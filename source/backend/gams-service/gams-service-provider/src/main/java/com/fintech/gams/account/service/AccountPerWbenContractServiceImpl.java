/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerWbenContract;
import com.fintech.gams.account.mapper.AccountPerWbenContractMapper;
import com.fintech.gams.bean.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人WBen合约 服务实现类
 * <p>File：AccountPerWbenContractServiceImpl.java </p>
 * <p>Title: AccountPerWbenContractServiceImpl </p>
 * <p>Description:AccountPerWbenContractServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerWbenContractServiceImpl extends GenericServiceImpl<AccountPerWbenContract> implements AccountPerWbenContractService
{

    protected AccountPerWbenContractMapper accountPerWbenContractMapper;

    @Autowired(required = false)
    public AccountPerWbenContractServiceImpl(AccountPerWbenContractMapper accountPerWbenContractMapper)
    {
        super(accountPerWbenContractMapper);
        this.accountPerWbenContractMapper = accountPerWbenContractMapper;
    }
}
