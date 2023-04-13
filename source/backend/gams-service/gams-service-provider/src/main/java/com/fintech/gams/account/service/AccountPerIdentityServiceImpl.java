/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerIdentity;
import com.fintech.gams.account.mapper.AccountPerIdentityMapper;
import com.fintech.gams.account.model.AccountPerIdentityModel;
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
 * 个人身份信息 服务实现类
 * <p>File：AccountPerIdentityServiceImpl.java </p>
 * <p>Title: AccountPerIdentityServiceImpl </p>
 * <p>Description:AccountPerIdentityServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerIdentityServiceImpl extends GenericServiceImpl<AccountPerIdentity> implements AccountPerIdentityService
{

    protected AccountPerIdentityMapper accountPerIdentityMapper;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private RegionService regionService;

    @Autowired(required = false)
    public AccountPerIdentityServiceImpl(AccountPerIdentityMapper accountPerIdentityMapper)
    {
        super(accountPerIdentityMapper);
        this.accountPerIdentityMapper = accountPerIdentityMapper;
    }


    @Override
    public List<AccountPerIdentityModel> findList(AccountPerIdentity accountPerIdentity, String lang) {
        List<AccountPerIdentity> accountPerIdentities = accountPerIdentityMapper.findList(accountPerIdentity);
        //查找 个人身份信息 中关联的字典表信息
        List<AccountPerIdentityModel> accountPerIdentityModels =new LinkedList<>();
        for(AccountPerIdentity perIdentity:accountPerIdentities){
            AccountPerIdentityModel accountPerIdentityModel = new AccountPerIdentityModel()
                    .convertToModel(perIdentity)
                    .setIdCardType(dictionaryService.findDictionary("cardType",perIdentity.getIdCardType(), lang))
                    .setIssuingCountry(regionService.findBysCode(perIdentity.getIssuingCountry()));
            accountPerIdentityModels.add(accountPerIdentityModel);
        }
        return accountPerIdentityModels;
    }
}
