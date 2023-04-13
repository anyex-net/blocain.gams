/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.account.mapper.AccountCommonTradeLocationMapper;
import com.fintech.gams.account.model.AccountCoTradeExperienceModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.service.DictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.gams.account.entity.AccountCoTradeExperience;
import com.fintech.gams.account.mapper.AccountCoTradeExperienceMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 公司交易经验信息 服务实现类
 * <p>File：AccountCoTradeExperienceServiceImpl.java </p>
 * <p>Title: AccountCoTradeExperienceServiceImpl </p>
 * <p>Description:AccountCoTradeExperienceServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoTradeExperienceServiceImpl extends GenericServiceImpl<AccountCoTradeExperience> implements AccountCoTradeExperienceService
{
    protected AccountCoTradeExperienceMapper accountCoTradeExperienceMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private AccountCommonTradeLocationMapper accountCommonTradeLocationMapper;

    @Autowired(required = false)
    public AccountCoTradeExperienceServiceImpl(AccountCoTradeExperienceMapper accountCoTradeExperienceMapper)
    {
        super(accountCoTradeExperienceMapper);
        this.accountCoTradeExperienceMapper = accountCoTradeExperienceMapper;
    }


    @Override
    public List<AccountCoTradeExperienceModel> findList(AccountCoTradeExperience accountCoTradeExperience, String lang) {
        List<AccountCoTradeExperience> accountCoTradeExperiences = accountCoTradeExperienceMapper.findList(accountCoTradeExperience);
        List<AccountCoTradeExperienceModel> accountCoTradeExperienceModels = new LinkedList<>();
        for(AccountCoTradeExperience tradeExp : accountCoTradeExperiences) {
            AccountCoTradeExperienceModel accountCoTradeExperienceModel = new AccountCoTradeExperienceModel()
                    .convertToModel(tradeExp)
                    .setKnowledgeLevel(dictionaryService.findDictionary("knowledge",tradeExp.getKnowledgeLevel(),lang))
                    .setTansactionType(dictionaryService.findDictionary("tansactionType",tradeExp.getTansactionType(),lang))
                    .setTradeExperience(dictionaryService.findDictionary("tradingExperience",tradeExp.getTradeExperience(),lang))
                    .setYearTransactionsPerNumber(dictionaryService.findDictionary("yearTransactionsPerNumber",tradeExp.getYearTransactionsPerNumber(),lang));
            //获取交易地点及其区域信息
            if(tradeExp.getTansactionType() != null  && tradeExp.getTradingLocation() !=null){
                AccountCommonTradeLocation accountCommonTradeLocation =new AccountCommonTradeLocation();
                accountCommonTradeLocation.setTansactionType(tradeExp.getTansactionType());
                List<String> locationStrsList = Arrays.asList(tradeExp.getTradingLocation().split(","));
                List<AccountCommonTradeLocation> accountCommonTradeLocationList = new ArrayList<>();
                for (String locationStr:locationStrsList){
                    accountCommonTradeLocation.setTansactionLocation(locationStr);
                    accountCommonTradeLocationList.addAll(accountCommonTradeLocationMapper.findList(accountCommonTradeLocation));
                }
                accountCoTradeExperienceModel.setTradingLocation(accountCommonTradeLocationList);
            }
            accountCoTradeExperienceModels.add(accountCoTradeExperienceModel);
        }
        return accountCoTradeExperienceModels;
    }
}
