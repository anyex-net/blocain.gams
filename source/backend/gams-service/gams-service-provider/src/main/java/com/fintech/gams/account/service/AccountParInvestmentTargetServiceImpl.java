/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParInvestmentTargetModel;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.service.DictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParInvestmentTarget;
import com.fintech.gams.account.mapper.AccountParInvestmentTargetMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合伙企业投资目标信息 服务实现类
 * <p>File：AccountParInvestmentTargetServiceImpl.java </p>
 * <p>Title: AccountParInvestmentTargetServiceImpl </p>
 * <p>Description:AccountParInvestmentTargetServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParInvestmentTargetService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParInvestmentTargetServiceImpl extends GenericServiceImpl<AccountParInvestmentTarget> implements AccountParInvestmentTargetService
{

    protected AccountParInvestmentTargetMapper accountParInvestmentTargetMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;

    @Autowired(required = false)
    public AccountParInvestmentTargetServiceImpl(AccountParInvestmentTargetMapper accountParInvestmentTargetMapper)
    {
        super(accountParInvestmentTargetMapper);
        this.accountParInvestmentTargetMapper = accountParInvestmentTargetMapper;
    }

    @Override
    public List<AccountParInvestmentTargetModel> findList(AccountParInvestmentTarget accountParInvestmentTarget, String lang) {
        List<AccountParInvestmentTarget> accountPerInvestTargets = accountParInvestmentTargetMapper.findList(accountParInvestmentTarget);
        List<AccountParInvestmentTargetModel> accountParInvestmentTargetModels = new ArrayList<>();
        for(AccountParInvestmentTarget investTarget:accountPerInvestTargets){
            AccountParInvestmentTargetModel  accountParInvestmentTargetModel = new AccountParInvestmentTargetModel()
                    .convertToModel(investTarget);
            List<String> investTargetList = Arrays.asList(investTarget.getInvestmentTarget().split(","));
            List<Dictionary> dictionaries =new ArrayList<>();
            for(String investTargetStr:investTargetList){
                dictionaries.add(dictionaryService.findDictionary("investmentTargetType",investTargetStr,lang));
            }
            accountParInvestmentTargetModel.setInvestmentTarget(dictionaries);
            accountParInvestmentTargetModels.add(accountParInvestmentTargetModel);
        }
        return accountParInvestmentTargetModels;
    }
}
