/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountCoInvestmentTargetModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.gams.account.entity.AccountCoInvestmentTarget;
import com.fintech.gams.account.mapper.AccountCoInvestmentTargetMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 公司投资目标信息 服务实现类
 * <p>File：AccountCoInvestmentTargetServiceImpl.java </p>
 * <p>Title: AccountCoInvestmentTargetServiceImpl </p>
 * <p>Description:AccountCoInvestmentTargetServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoInvestmentTargetServiceImpl extends GenericServiceImpl<AccountCoInvestmentTarget> implements AccountCoInvestmentTargetService
{

    protected AccountCoInvestmentTargetMapper accountCoInvestmentTargetMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;

    @Autowired(required = false)
    public AccountCoInvestmentTargetServiceImpl(AccountCoInvestmentTargetMapper accountCoInvestmentTargetMapper)
    {
        super(accountCoInvestmentTargetMapper);
        this.accountCoInvestmentTargetMapper = accountCoInvestmentTargetMapper;
    }


    @Override
    public AccountCoInvestmentTarget findByAccountId(Long accountId) throws BusinessException {
        return accountCoInvestmentTargetMapper.findByAccountId(accountId);
    }

    @Override
    public List<AccountCoInvestmentTargetModel> findList(AccountCoInvestmentTarget accountCoInvestmentTarget, String lang) {
        List<AccountCoInvestmentTarget> accountCoInvestTargets = accountCoInvestmentTargetMapper.findList(accountCoInvestmentTarget);
        List<AccountCoInvestmentTargetModel> accountCoInvestmentTargetModels = new ArrayList<>();
        for(AccountCoInvestmentTarget investmentTarget:accountCoInvestTargets){
            AccountCoInvestmentTargetModel  accountCoInvestTargetModel = new AccountCoInvestmentTargetModel()
                    .convertToModel(investmentTarget);
            List<String> investTargetList = Arrays.asList(investmentTarget.getInvestmentTarget().split(","));
            List<Dictionary> dictionaries =new ArrayList<>();
            for(String investTargetStr:investTargetList){
                dictionaries.add(dictionaryService.findDictionary("investmentTargetType",investTargetStr,lang));
            }
            accountCoInvestTargetModel.setInvestmentTarget(dictionaries);
            accountCoInvestmentTargetModels.add(accountCoInvestTargetModel);
        }
        return accountCoInvestmentTargetModels;
    }
}
