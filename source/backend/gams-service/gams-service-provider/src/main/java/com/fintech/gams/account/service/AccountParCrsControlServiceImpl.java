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

import com.fintech.gams.account.entity.AccountParCrsControl;
import com.fintech.gams.account.mapper.AccountParCrsControlMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 合伙企业CRS控权人自我证明表格(CRS-CP) 服务实现类
 * <p>File：AccountParCrsControlServiceImpl.java </p>
 * <p>Title: AccountParCrsControlServiceImpl </p>
 * <p>Description:AccountParCrsControlServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParCrsControlService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParCrsControlServiceImpl extends GenericServiceImpl<AccountParCrsControl> implements AccountParCrsControlService
{

    protected AccountParCrsControlMapper accountParCrsControlMapper;

    @Autowired(required = false)
    public AccountParCrsControlServiceImpl(AccountParCrsControlMapper accountParCrsControlMapper)
    {
        super(accountParCrsControlMapper);
        this.accountParCrsControlMapper = accountParCrsControlMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByParCrsEntityContrId(Long parCrsEntityContrId) throws BusinessException {
        if (null == parCrsEntityContrId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountParCrsControlMapper.removeByParCrsEntityContrId(parCrsEntityContrId);
    }
}
