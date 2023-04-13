/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerContact;
import com.fintech.gams.account.mapper.AccountPerContactMapper;
import com.fintech.gams.account.model.AccountPerContactModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.RegionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 个人联系方式 服务实现类
 * <p>File：AccountPerContactServiceImpl.java </p>
 * <p>Title: AccountPerContactServiceImpl </p>
 * <p>Description:AccountPerContactServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerContactServiceImpl extends GenericServiceImpl<AccountPerContact> implements AccountPerContactService
{

    protected AccountPerContactMapper accountPerContactMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private RegionService regionService;

    @Autowired(required = false)
    public AccountPerContactServiceImpl(AccountPerContactMapper accountPerContactMapper)
    {
        super(accountPerContactMapper);
        this.accountPerContactMapper = accountPerContactMapper;
    }

    @Override
    public List<AccountPerContactModel> findList(AccountPerContact accountPerContact, String lang) {
        List<AccountPerContact> accountPerContacts = accountPerContactMapper.findList(accountPerContact);
        //查找 个人联系方式 中关联的字典表信息
        List<AccountPerContactModel> accountPerContactModels =new LinkedList<>();
        for(AccountPerContact perContact:accountPerContacts){
            AccountPerContactModel accountPerContactModel = new AccountPerContactModel()
                    .convertToModel(perContact)
                    .setContactNumberType(dictionaryService.findDictionary("phoneNumberType",perContact.getContactNumberType(),lang))
                    .setCountry(regionService.findBysCode(perContact.getCountry()));
            accountPerContactModels.add(accountPerContactModel);
        }
        return accountPerContactModels;
    }
}
