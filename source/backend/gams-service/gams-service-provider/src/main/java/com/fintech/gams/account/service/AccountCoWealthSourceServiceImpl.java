/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;


import com.fintech.gams.account.model.AccountCoWealthSourceModel;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.common.service.DictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.gams.account.entity.AccountCoWealthSource;
import com.fintech.gams.account.mapper.AccountCoWealthSourceMapper;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;


/**
 * 公司财富来源 服务实现类
 * <p>File：AccountCoWealthSourceServiceImpl.java </p>
 * <p>Title: AccountCoWealthSourceServiceImpl </p>
 * <p>Description:AccountCoWealthSourceServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoWealthSourceServiceImpl extends GenericServiceImpl<AccountCoWealthSource> implements AccountCoWealthSourceService {

    protected AccountCoWealthSourceMapper accountCoWealthSourceMapper;

    @Autowired(required = false)
    private DictionaryService dictionaryService;

    @Autowired(required = false)
    public AccountCoWealthSourceServiceImpl(AccountCoWealthSourceMapper accountCoWealthSourceMapper) {
        super(accountCoWealthSourceMapper);
        this.accountCoWealthSourceMapper = accountCoWealthSourceMapper;
    }

    @Override
    public List<AccountCoWealthSourceModel> findList(AccountCoWealthSource accountCoWealthSource, String lang) {
        List<AccountCoWealthSource> accountCoWealthSources = accountCoWealthSourceMapper.findList(accountCoWealthSource);
        List<AccountCoWealthSourceModel> accountCoWealthSourceModels = new ArrayList<>();
        for(AccountCoWealthSource wealthSource : accountCoWealthSources){
            AccountCoWealthSourceModel accountCoWealthSourceModel =new AccountCoWealthSourceModel()
                    .convertToModel(wealthSource);
            if(null != wealthSource.getIncomeType()){
                accountCoWealthSourceModel.setIncomeType(dictionaryService.findDictionary("wealthSource",wealthSource.getIncomeType(),lang));
            }
            accountCoWealthSourceModels.add(accountCoWealthSourceModel);
        }
        return accountCoWealthSourceModels;
    }
}


