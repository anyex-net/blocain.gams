/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParTaxesModel;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParTaxes;
import com.fintech.gams.account.mapper.AccountParTaxesMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 合伙企业税务居住地 服务实现类
 * <p>File：AccountParTaxesServiceImpl.java </p>
 * <p>Title: AccountParTaxesServiceImpl </p>
 * <p>Description:AccountParTaxesServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParTaxesService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParTaxesServiceImpl extends GenericServiceImpl<AccountParTaxes> implements AccountParTaxesService
{

    protected AccountParTaxesMapper accountParTaxesMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private RegionService regionService;

    @Autowired(required = false)
    public AccountParTaxesServiceImpl(AccountParTaxesMapper accountParTaxesMapper)
    {
        super(accountParTaxesMapper);
        this.accountParTaxesMapper = accountParTaxesMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByParPersonalId(Long parPersonalId) throws BusinessException {
        return accountParTaxesMapper.removeByParPersonalId(parPersonalId);
    }

    @Override
    public List<AccountParTaxesModel> findList(AccountParTaxes accountParTaxes, String lang) {
        List<AccountParTaxes> accountParTaxesList = accountParTaxesMapper.findList(accountParTaxes);
        List<AccountParTaxesModel> accountParTaxesModels =new ArrayList<>();
        for(AccountParTaxes taxes : accountParTaxesList){
            AccountParTaxesModel accountParTaxesModel =new AccountParTaxesModel()
                    .convertToModel(taxes)
                    .setTaxResidenceCountry(regionService.findBysCode(taxes.getTaxResidenceCountry()))
                    .setNoTaxIdentificationNumber(null)
                    .setNoTaxIdentificationNumber(dictionaryService.findDictionary("taxIdentificationNumber",taxes.getNoTaxIdentificationNumber(),lang));
            if(accountParTaxesModel.getNoTaxIdentificationNumber() == null || StringUtils.isBlank(accountParTaxesModel.getNoTaxIdentificationNumber().getDictValue())){
                Dictionary dictionary = new Dictionary();
                dictionary.setDictDest(taxes.getNoTaxIdentificationNumber());
                accountParTaxesModel.setNoTaxIdentificationNumber(dictionary);
            }
            if(accountParTaxesModel.getNoTaxIdentificationNumber() != null && StringUtils.isBlank(accountParTaxesModel.getNoTaxIdentificationNumber().getDictDest())==false){
                //替换 占位字段 country
                if(accountParTaxesModel.getNoTaxIdentificationNumber().getDictDest().contains("country") && accountParTaxesModel.getTaxResidenceCountry()!=null){
                    if(lang.endsWith("zh_CN")){
                        accountParTaxesModel.getNoTaxIdentificationNumber().setDictDest(accountParTaxesModel.getNoTaxIdentificationNumber().getDictDest().replace("country",accountParTaxesModel.getTaxResidenceCountry().getCnName()));
                    }
                    if(lang.endsWith("en_US")){
                        accountParTaxesModel.getNoTaxIdentificationNumber().setDictDest(accountParTaxesModel.getNoTaxIdentificationNumber().getDictDest().replace("country",accountParTaxesModel.getTaxResidenceCountry().getEnName()));
                    }
                }
            }
            accountParTaxesModels.add(accountParTaxesModel);
        }
        return accountParTaxesModels;
    }
}
