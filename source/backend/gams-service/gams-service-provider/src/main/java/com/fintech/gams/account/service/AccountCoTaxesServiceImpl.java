/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountCoTaxes;
import com.fintech.gams.account.mapper.AccountCoTaxesMapper;
import com.fintech.gams.account.model.AccountCoTaxesModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司税务居住地 服务实现类
 * <p>File：AccountCoTaxesServiceImpl.java </p>
 * <p>Title: AccountCoTaxesServiceImpl </p>
 * <p>Description:AccountCoTaxesServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoTaxesServiceImpl extends GenericServiceImpl<AccountCoTaxes> implements AccountCoTaxesService
{

    protected AccountCoTaxesMapper accountCoTaxesMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private RegionService regionService;
    @Autowired(required = false)
    public AccountCoTaxesServiceImpl(AccountCoTaxesMapper accountCoTaxesMapper)
    {
        super(accountCoTaxesMapper);
        this.accountCoTaxesMapper = accountCoTaxesMapper;
    }


    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByCoPersonalId(Long coPersonalId) throws BusinessException {
        return accountCoTaxesMapper.removeByCoPersonalId(coPersonalId);
    }

    @Override
    public List<AccountCoTaxesModel> findList(AccountCoTaxes coTaxes, String lang) {
        List<AccountCoTaxes> accountCoTaxesList = accountCoTaxesMapper.findList(coTaxes);
        List<AccountCoTaxesModel> accountCoTaxesModels =new ArrayList<>();
        for(AccountCoTaxes taxes : accountCoTaxesList){
            AccountCoTaxesModel accountCoTaxesModel =new AccountCoTaxesModel()
                    .convertToModel(taxes)
                    .setTaxResidenceCountry(regionService.findBysCode(taxes.getTaxResidenceCountry()))
                    .setNoTaxIdentificationNumber(dictionaryService.findDictionary("taxIdentificationNumber",taxes.getNoTaxIdentificationNumber(),lang));
            if(accountCoTaxesModel.getNoTaxIdentificationNumber() == null || StringUtils.isBlank(accountCoTaxesModel.getNoTaxIdentificationNumber().getDictValue())){
                Dictionary dictionary = new Dictionary();
                dictionary.setDictDest(taxes.getNoTaxIdentificationNumber());
                accountCoTaxesModel.setNoTaxIdentificationNumber(dictionary);
            }
            if(accountCoTaxesModel.getNoTaxIdentificationNumber() != null && StringUtils.isBlank(accountCoTaxesModel.getNoTaxIdentificationNumber().getDictDest())==false){
                //替换 占位字段 country
                if(accountCoTaxesModel.getNoTaxIdentificationNumber().getDictDest().contains("country") && accountCoTaxesModel.getTaxResidenceCountry()!=null){
                    if(lang.endsWith("zh_CN")){
                        accountCoTaxesModel.getNoTaxIdentificationNumber().setDictDest(accountCoTaxesModel.getNoTaxIdentificationNumber().getDictDest().replace("country",accountCoTaxesModel.getTaxResidenceCountry().getCnName()));
                    }
                    if(lang.endsWith("en_US")){
                        accountCoTaxesModel.getNoTaxIdentificationNumber().setDictDest(accountCoTaxesModel.getNoTaxIdentificationNumber().getDictDest().replace("country",accountCoTaxesModel.getTaxResidenceCountry().getEnName()));
                    }
                }
            }
            accountCoTaxesModels.add(accountCoTaxesModel);
        }
        return accountCoTaxesModels;
    }
}
