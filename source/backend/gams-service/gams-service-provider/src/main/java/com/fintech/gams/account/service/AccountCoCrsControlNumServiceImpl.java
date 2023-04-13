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

import com.fintech.gams.account.entity.AccountCoCrsControlNum;
import com.fintech.gams.account.mapper.AccountCoCrsControlNumMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公司CRS控权人自我证明控权人编号 服务实现类
 * <p>File：AccountCoCrsControlNumServiceImpl.java </p>
 * <p>Title: AccountCoCrsControlNumServiceImpl </p>
 * <p>Description:AccountCoCrsControlNumServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountCoCrsControlNumService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountCoCrsControlNumServiceImpl extends GenericServiceImpl<AccountCoCrsControlNum> implements AccountCoCrsControlNumService
{

    protected AccountCoCrsControlNumMapper accountCoCrsControlNumMapper;

    @Autowired(required = false)
    public AccountCoCrsControlNumServiceImpl(AccountCoCrsControlNumMapper accountCoCrsControlNumMapper)
    {
        super(accountCoCrsControlNumMapper);
        this.accountCoCrsControlNumMapper = accountCoCrsControlNumMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByCoCrsEntityContrId(Long coCrsEntityContrId) throws BusinessException {
        if (null == coCrsEntityContrId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountCoCrsControlNumMapper.removeByCoCrsEntityContrId(coCrsEntityContrId);
    }
}
