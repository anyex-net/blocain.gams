/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerInvestTarget;
import com.fintech.gams.account.mapper.AccountPerInvestTargetMapper;
import com.fintech.gams.account.model.AccountPerInvestTargetModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.service.DictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 个人投资目标 服务实现类
 * <p>File：AccountPerInvestTargetServiceImpl.java </p>
 * <p>Title: AccountPerInvestTargetServiceImpl </p>
 * <p>Description:AccountPerInvestTargetServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerInvestTargetServiceImpl extends GenericServiceImpl<AccountPerInvestTarget> implements AccountPerInvestTargetService
{

    protected AccountPerInvestTargetMapper accountPerInvestTargetMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;

    @Autowired(required = false)
    public AccountPerInvestTargetServiceImpl(AccountPerInvestTargetMapper accountPerInvestTargetMapper)
    {
        super(accountPerInvestTargetMapper);
        this.accountPerInvestTargetMapper = accountPerInvestTargetMapper;
    }

    @Override
    public List<AccountPerInvestTargetModel> findList(AccountPerInvestTarget accountPerInvestTarget, String lang) {
        List<AccountPerInvestTarget> accountPerInvestTargets = accountPerInvestTargetMapper.findList(accountPerInvestTarget);
        List<AccountPerInvestTargetModel> accountPerInvestTargetModels = new ArrayList<>();
        for(AccountPerInvestTarget investTarget:accountPerInvestTargets){
            AccountPerInvestTargetModel  accountPerInvestTargetModel = new AccountPerInvestTargetModel()
                    .convertToModel(investTarget);
            List<String> investTargetList = Arrays.asList(investTarget.getInvestmentTarget().split(","));
            List<Dictionary> dictionaries =new ArrayList<>();
            for(String investTargetStr:investTargetList){
                dictionaries.add(dictionaryService.findDictionary("investmentTargetType",investTargetStr,lang));
            }
            accountPerInvestTargetModel.setInvestmentTarget(dictionaries);
            accountPerInvestTargetModels.add(accountPerInvestTargetModel);
        }
        return accountPerInvestTargetModels;
    }
}
