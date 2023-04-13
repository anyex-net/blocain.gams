/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParWealthSourceModel;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.common.service.DictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParWealthSource;
import com.fintech.gams.account.mapper.AccountParWealthSourceMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 合伙企业财富来源 服务实现类
 * <p>File：AccountParWealthSourceServiceImpl.java </p>
 * <p>Title: AccountParWealthSourceServiceImpl </p>
 * <p>Description:AccountParWealthSourceServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParWealthSourceService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParWealthSourceServiceImpl extends GenericServiceImpl<AccountParWealthSource> implements AccountParWealthSourceService
{

    protected AccountParWealthSourceMapper accountParWealthSourceMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;

    @Autowired(required = false)
    public AccountParWealthSourceServiceImpl(AccountParWealthSourceMapper accountParWealthSourceMapper)
    {
        super(accountParWealthSourceMapper);
        this.accountParWealthSourceMapper = accountParWealthSourceMapper;
    }

    @Override
    public List<AccountParWealthSourceModel> findList(AccountParWealthSource accountParWealthSource, String lang) {
        List<AccountParWealthSource> accountParWealthSources = accountParWealthSourceMapper.findList(accountParWealthSource);
        List<AccountParWealthSourceModel> accountParWealthSourceModels = new ArrayList<>();
        for(AccountParWealthSource wealthSource : accountParWealthSources){
            AccountParWealthSourceModel accountParWealthSourceModel =new AccountParWealthSourceModel()
                    .convertToModel(wealthSource);
            if(null != wealthSource.getIncomeType()){
                accountParWealthSourceModel.setIncomeType(dictionaryService.findDictionary("wealthSource",wealthSource.getIncomeType(),lang));
            }
            accountParWealthSourceModels.add(accountParWealthSourceModel);
        }
        return accountParWealthSourceModels;
    }
}
