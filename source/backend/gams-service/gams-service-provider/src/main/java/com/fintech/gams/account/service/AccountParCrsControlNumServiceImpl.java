/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParCrsControlNum;
import com.fintech.gams.account.mapper.AccountParCrsControlNumMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 合伙企业CRS控权人自我证明控权人编号 服务实现类
 * <p>File：AccountParCrsControlNumServiceImpl.java </p>
 * <p>Title: AccountParCrsControlNumServiceImpl </p>
 * <p>Description:AccountParCrsControlNumServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParCrsControlNumService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParCrsControlNumServiceImpl extends GenericServiceImpl<AccountParCrsControlNum> implements AccountParCrsControlNumService
{

    protected AccountParCrsControlNumMapper accountParCrsControlNumMapper;

    @Autowired(required = false)
    public AccountParCrsControlNumServiceImpl(AccountParCrsControlNumMapper accountParCrsControlNumMapper)
    {
        super(accountParCrsControlNumMapper);
        this.accountParCrsControlNumMapper = accountParCrsControlNumMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByParCrsEntityContrId(Long parrCrsEntityContrId) throws BusinessException {
        if (null == parrCrsEntityContrId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountParCrsControlNumMapper.removeByParCrsEntityContrId(parrCrsEntityContrId);
    }
}
