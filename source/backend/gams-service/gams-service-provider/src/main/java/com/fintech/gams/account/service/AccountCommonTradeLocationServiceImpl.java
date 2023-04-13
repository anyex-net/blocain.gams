/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.mapper.AccountCommonTradeLocationMapper;

import java.util.List;

/**
 * ACCOUNT_COMMON_TRADE_LOCATION 服务实现类
 * <p>File：AccountCommonTradeLocationServiceImpl.java </p>
 * <p>Title: AccountCommonTradeLocationServiceImpl </p>
 * <p>Description:AccountCommonTradeLocationServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountCommonTradeLocationService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountCommonTradeLocationServiceImpl extends GenericServiceImpl<AccountCommonTradeLocation> implements AccountCommonTradeLocationService
{

    protected AccountCommonTradeLocationMapper accountCommonTradeLocationMapper;

    @Autowired(required = false)
    public AccountCommonTradeLocationServiceImpl(AccountCommonTradeLocationMapper accountCommonTradeLocationMapper)
    {
        super(accountCommonTradeLocationMapper);
        this.accountCommonTradeLocationMapper = accountCommonTradeLocationMapper;
    }

    @Override
    public List<AccountCommonTradeLocation> findByTansactionType(String tansactionType) {
        if (StringUtils.isBlank(tansactionType))
        { // 参数判断
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        return  accountCommonTradeLocationMapper.findByTansactionType(tansactionType);
    }
}
