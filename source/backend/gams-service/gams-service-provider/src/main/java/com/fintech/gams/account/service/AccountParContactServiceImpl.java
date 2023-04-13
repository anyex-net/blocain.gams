/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParContactModel;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParContact;
import com.fintech.gams.account.mapper.AccountParContactMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * 合伙企业联系信息 服务实现类
 * <p>File：AccountParContactServiceImpl.java </p>
 * <p>Title: AccountParContactServiceImpl </p>
 * <p>Description:AccountParContactServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParContactService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParContactServiceImpl extends GenericServiceImpl<AccountParContact> implements AccountParContactService
{

    protected AccountParContactMapper accountParContactMapper;
    @Autowired(required = false)
    protected RegionService regionService;
    @Autowired(required = false)
    private DictionaryService dictionaryService;

    @Autowired(required = false)
    public AccountParContactServiceImpl(AccountParContactMapper accountParContactMapper)
    {
        super(accountParContactMapper);
        this.accountParContactMapper = accountParContactMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByParPersonalId(Long parPersonalId) throws BusinessException {
        return accountParContactMapper.removeByParPersonalId(parPersonalId);
    }

    @Override
    public List<AccountParContactModel> findList(AccountParContact accountParContact, String lang) {
        List<AccountParContact> accountParContacts = accountParContactMapper.findList(accountParContact);
        List<AccountParContactModel> accountParContactModels =new LinkedList<>();
        for(AccountParContact contact : accountParContacts){
            AccountParContactModel accountParContactModel = new AccountParContactModel()
                    .convertToModel(contact)
                    .setContactNumberType(dictionaryService.findDictionary("phoneNumberType",contact.getContactNumberType(),lang))
                    .setCountry(regionService.findBysCode(contact.getCountry()))
                    .setFoundingCountry(regionService.findBysCode(contact.getFoundingCountry()));
            accountParContactModels.add(accountParContactModel);
        }
        return accountParContactModels;
    }
}
