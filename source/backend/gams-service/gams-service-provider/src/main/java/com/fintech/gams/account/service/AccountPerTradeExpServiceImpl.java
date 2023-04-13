/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountCommonTradeLocation;
import com.fintech.gams.account.entity.AccountPerTradeExp;
import com.fintech.gams.account.mapper.AccountCommonTradeLocationMapper;
import com.fintech.gams.account.mapper.AccountPerTradeExpMapper;
import com.fintech.gams.account.model.AccountPerTradeExpModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 个人交易经验信息 服务实现类
 * <p>File：AccountPerTradeExpServiceImpl.java </p>
 * <p>Title: AccountPerTradeExpServiceImpl </p>
 * <p>Description:AccountPerTradeExpServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerTradeExpServiceImpl extends GenericServiceImpl<AccountPerTradeExp> implements AccountPerTradeExpService {

    protected AccountPerTradeExpMapper accountPerTradeExpMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private AccountCommonTradeLocationMapper accountCommonTradeLocationMapper;

    @Autowired(required = false)
    public AccountPerTradeExpServiceImpl(AccountPerTradeExpMapper accountPerTradeExpMapper) {
        super(accountPerTradeExpMapper);
        this.accountPerTradeExpMapper = accountPerTradeExpMapper;
    }


    @Override
    public List<AccountPerTradeExpModel> findList(AccountPerTradeExp accountPerTradeExp, String lang) {
        List<AccountPerTradeExp>accountPerTradeExps = accountPerTradeExpMapper.findList(accountPerTradeExp);
        List<AccountPerTradeExpModel> accountPerTradeExpModels = new LinkedList<>();
        for(AccountPerTradeExp tradeExp : accountPerTradeExps) {
            AccountPerTradeExpModel accountPerTradeExpModel = new AccountPerTradeExpModel()
                    .convertToModel(tradeExp)
                    .setKnowledgeLevel(dictionaryService.findDictionary("knowledge",tradeExp.getKnowledgeLevel(),lang))
                    .setTansactionType(dictionaryService.findDictionary("tansactionType",tradeExp.getTansactionType(),lang))
                    .setTradingExperience(dictionaryService.findDictionary("tradingExperience",tradeExp.getTradingExperience(),lang))
                    .setYearTransactionsPerNumber(dictionaryService.findDictionary("yearTransactionsPerNumber",tradeExp.getYearTransactionsPerNumber(),lang));
            if(tradeExp.getTansactionType() !=null && tradeExp.getTradingLocation() !=null ){
                //获取交易地点及其区域信息
                AccountCommonTradeLocation accountCommonTradeLocation =new AccountCommonTradeLocation();
                accountCommonTradeLocation.setTansactionType(tradeExp.getTansactionType());
                List<String> locationStrsList = Arrays.asList(tradeExp.getTradingLocation().split(","));
                List<AccountCommonTradeLocation> accountCommonTradeLocationList = new ArrayList<>();
                for (String locationStr:locationStrsList){
                    accountCommonTradeLocation.setTansactionLocation(locationStr);
                    accountCommonTradeLocationList.addAll(accountCommonTradeLocationMapper.findList(accountCommonTradeLocation));
                }
                accountPerTradeExpModel.setTradingLocation(accountCommonTradeLocationList);
            }
            accountPerTradeExpModels.add(accountPerTradeExpModel);
        }
        return accountPerTradeExpModels;
    }
}
