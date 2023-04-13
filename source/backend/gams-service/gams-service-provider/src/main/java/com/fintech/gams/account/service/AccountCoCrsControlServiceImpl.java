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

import com.fintech.gams.account.entity.AccountCoCrsControl;
import com.fintech.gams.account.mapper.AccountCoCrsControlMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公司CRS控权人自我证明表格 服务实现类
 * <p>File：AccountCoCrsControlServiceImpl.java </p>
 * <p>Title: AccountCoCrsControlServiceImpl </p>
 * <p>Description:AccountCoCrsControlServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountCoCrsControlService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountCoCrsControlServiceImpl extends GenericServiceImpl<AccountCoCrsControl> implements AccountCoCrsControlService
{

    protected AccountCoCrsControlMapper accountCoCrsControlMapper;

    @Autowired(required = false)
    public AccountCoCrsControlServiceImpl(AccountCoCrsControlMapper accountCoCrsControlMapper)
    {
        super(accountCoCrsControlMapper);
        this.accountCoCrsControlMapper = accountCoCrsControlMapper;
    }


    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByCoCrsEntityContrId(Long coCrsEntityContrId) throws BusinessException {
        if (null == coCrsEntityContrId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountCoCrsControlMapper.removeByCoCrsEntityContrId(coCrsEntityContrId);
    }
}
