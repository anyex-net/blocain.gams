/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;


import com.fintech.gams.account.entity.AccountPerTaxes;
import com.fintech.gams.account.mapper.AccountPerTaxesMapper;
import com.fintech.gams.account.model.AccountPerTaxesModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人税务居住地 服务实现类
 * <p>File：AccountPerTaxesServiceImpl.java </p>
 * <p>Title: AccountPerTaxesServiceImpl </p>
 * <p>Description:AccountPerTaxesServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerTaxesServiceImpl extends GenericServiceImpl<AccountPerTaxes> implements AccountPerTaxesService
{

    protected AccountPerTaxesMapper accountPerTaxesMapper;

    @Autowired(required = false)
    public AccountPerTaxesServiceImpl(AccountPerTaxesMapper accountPerTaxesMapper)
    {
        super(accountPerTaxesMapper);
        this.accountPerTaxesMapper = accountPerTaxesMapper;
    }

    @Autowired(required = false)
    RegionService regionService;
    @Autowired(required = false)
    DictionaryService dictionaryService;

    @Override
    public List<AccountPerTaxesModel> findList(AccountPerTaxes perTaxes, String lang) {
        List<AccountPerTaxes> accountPerTaxes=accountPerTaxesMapper.findList(perTaxes);
        List<AccountPerTaxesModel> accountPerTaxesModels = new ArrayList<>();
        for(AccountPerTaxes taxes:accountPerTaxes){
            AccountPerTaxesModel accountPerTaxesModel = new AccountPerTaxesModel()
                    .convertToModel(taxes)
                    .setTaxResidenceCountry(regionService.findBysCode(taxes.getTaxResidenceCountry()));
            if(StringUtils.isBlank(taxes.getNoTaxIdentificationNumber())==false){
                accountPerTaxesModel.setNoTaxIdentificationNumber(dictionaryService.findDictionary("taxIdentificationNumber",taxes.getNoTaxIdentificationNumber(),lang));
            }
           if(accountPerTaxesModel.getNoTaxIdentificationNumber() == null || StringUtils.isBlank(accountPerTaxesModel.getNoTaxIdentificationNumber().getDictValue())){
                com.fintech.gams.common.entity.Dictionary dictionary =new Dictionary();
                dictionary.setDictDest(taxes.getNoTaxIdentificationNumber());
                accountPerTaxesModel.setNoTaxIdentificationNumber(dictionary);
            }
            if(accountPerTaxesModel.getNoTaxIdentificationNumber() != null && StringUtils.isBlank(accountPerTaxesModel.getNoTaxIdentificationNumber().getDictDest())==false){
                //替换 占位字段 country
                if(accountPerTaxesModel.getNoTaxIdentificationNumber().getDictDest().contains("country") && accountPerTaxesModel.getTaxResidenceCountry()!=null){
                    if(lang.endsWith("zh_CN")){
                        accountPerTaxesModel.getNoTaxIdentificationNumber().setDictDest(accountPerTaxesModel.getNoTaxIdentificationNumber().getDictDest().replace("country",accountPerTaxesModel.getTaxResidenceCountry().getCnName()));
                    }
                    if(lang.endsWith("en_US")){
                        accountPerTaxesModel.getNoTaxIdentificationNumber().setDictDest(accountPerTaxesModel.getNoTaxIdentificationNumber().getDictDest().replace("country",accountPerTaxesModel.getTaxResidenceCountry().getEnName()));
                    }
                }
            }
            accountPerTaxesModels.add(accountPerTaxesModel);
        }
        return accountPerTaxesModels;
    }
}
