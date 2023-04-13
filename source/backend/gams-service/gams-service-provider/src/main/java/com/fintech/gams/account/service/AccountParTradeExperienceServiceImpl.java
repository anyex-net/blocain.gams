/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.account.mapper.AccountCommonTradeLocationMapper;
import com.fintech.gams.account.model.AccountParTradeExperienceModel;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.common.service.DictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParTradeExperience;
import com.fintech.gams.account.mapper.AccountParTradeExperienceMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 合伙企业交易经验信息 服务实现类
 * <p>File：AccountParTradeExperienceServiceImpl.java </p>
 * <p>Title: AccountParTradeExperienceServiceImpl </p>
 * <p>Description:AccountParTradeExperienceServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParTradeExperienceService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParTradeExperienceServiceImpl extends GenericServiceImpl<AccountParTradeExperience> implements AccountParTradeExperienceService
{

    protected AccountParTradeExperienceMapper accountParTradeExperienceMapper;
    @Autowired(required = false)
    private AccountCommonTradeLocationMapper accountCommonTradeLocationMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;

    @Autowired(required = false)
    public AccountParTradeExperienceServiceImpl(AccountParTradeExperienceMapper accountParTradeExperienceMapper)
    {
        super(accountParTradeExperienceMapper);
        this.accountParTradeExperienceMapper = accountParTradeExperienceMapper;
    }

    @Override
    public List<AccountParTradeExperienceModel> findList(AccountParTradeExperience accountParTradeExperience, String lang) {
        List<AccountParTradeExperience> accountCoTradeExps = accountParTradeExperienceMapper.findList(accountParTradeExperience);
        List<AccountParTradeExperienceModel> accountParTradeExperienceModels = new LinkedList<>();
        for(AccountParTradeExperience tradeExp : accountCoTradeExps) {
            AccountParTradeExperienceModel accountParTradeExperienceModel = new AccountParTradeExperienceModel()
                    .convertToModel(tradeExp)
                    .setKnowledgeLevel(dictionaryService.findDictionary("knowledge",tradeExp.getKnowledgeLevel(),lang))
                    .setTansactionType(dictionaryService.findDictionary("tansactionType",tradeExp.getTansactionType(),lang))
                    .setTradeExperience(dictionaryService.findDictionary("tradingExperience",tradeExp.getTradeExperience(),lang))
                    .setYearTransactionsPerNumber(dictionaryService.findDictionary("yearTransactionsPerNumber",tradeExp.getYearTransactionsPerNumber(),lang));
            //获取交易地点及其区域信息
            if(tradeExp.getTansactionType() != null  && tradeExp.getTradingLocation() !=null ){
                AccountCommonTradeLocation accountCommonTradeLocation =new AccountCommonTradeLocation();
                accountCommonTradeLocation.setTansactionType(tradeExp.getTansactionType());
                List<String> locationStrsList = Arrays.asList(tradeExp.getTradingLocation().split(","));
                List<AccountCommonTradeLocation> accountCommonTradeLocationList = new ArrayList<>();
                for (String locationStr:locationStrsList){
                    accountCommonTradeLocation.setTansactionLocation(locationStr);
                    accountCommonTradeLocationList.addAll(accountCommonTradeLocationMapper.findList(accountCommonTradeLocation));
                }
                accountParTradeExperienceModel.setTradingLocation(accountCommonTradeLocationList);
            }
            accountParTradeExperienceModels.add(accountParTradeExperienceModel);
        }
        return accountParTradeExperienceModels;
    }
}
