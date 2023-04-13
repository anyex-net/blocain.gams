/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountCoContactModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.mapper.RegionMapper;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.gams.account.entity.AccountCoContact;
import com.fintech.gams.account.mapper.AccountCoContactMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * 公司联系信息 服务实现类
 * <p>File：AccountCoContactServiceImpl.java </p>
 * <p>Title: AccountCoContactServiceImpl </p>
 * <p>Description:AccountCoContactServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoContactServiceImpl extends GenericServiceImpl<AccountCoContact> implements AccountCoContactService
{

    protected AccountCoContactMapper accountCoContactMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private RegionService regionService;

    @Autowired(required = false)
    public AccountCoContactServiceImpl(AccountCoContactMapper accountCoContactMapper)
    {
        super(accountCoContactMapper);
        this.accountCoContactMapper = accountCoContactMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByCoPersonalId(Long coPersonalId) throws BusinessException {
        return accountCoContactMapper.removeByCoPersonalId(coPersonalId);
    }

    @Override
    public List<AccountCoContactModel> findList(AccountCoContact accountCoContact, String lang) {
        List<AccountCoContact> accountCoContacts =accountCoContactMapper.findList(accountCoContact);

        List<AccountCoContactModel> accountCoContactModels =new LinkedList<>();
        for(AccountCoContact contact:accountCoContacts){
            AccountCoContactModel accountCoContactModel = new AccountCoContactModel()
                    .convertToModel(contact)
                    .setContactNumberType(dictionaryService.findDictionary("phoneNumberType",contact.getContactNumberType(),lang))
                    .setCountry(regionService.findBysCode(contact.getCountry()))
                    .setFoundingCountry(regionService.findBysCode(contact.getFoundingCountry()));
            accountCoContactModels.add(accountCoContactModel);
        }
        return accountCoContactModels;
    }


}
