/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerAddress;
import com.fintech.gams.account.mapper.AccountPerAddressMapper;
import com.fintech.gams.account.model.AccountPerAddressModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.service.RegionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人地址 服务实现类
 * <p>File：AccountPerAddressServiceImpl.java </p>
 * <p>Title: AccountPerAddressServiceImpl </p>
 * <p>Description:AccountPerAddressServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountPerAddressServiceImpl extends GenericServiceImpl<AccountPerAddress> implements AccountPerAddressService
{
    protected AccountPerAddressMapper accountPerAddressMapper;
    @Autowired(required = false)
    private RegionService regionService;

    @Autowired(required = false)
    public AccountPerAddressServiceImpl(AccountPerAddressMapper accountPerAddressMapper)
    {
        super(accountPerAddressMapper);
        this.accountPerAddressMapper = accountPerAddressMapper;
    }

    @Override
    public List<AccountPerAddressModel> findList(AccountPerAddress accountPerAddress, String lang) {
        List<AccountPerAddress> accountPerAddresses =accountPerAddressMapper.findList(accountPerAddress);
        List<AccountPerAddressModel> accountPerAddressModels = new ArrayList<>();
        for(AccountPerAddress address:accountPerAddresses){
            AccountPerAddressModel accountPerAddressModel = new AccountPerAddressModel()
                    .convertToModel(address)
                    .setCountry(regionService.findBysCode(address.getCountry()))
                    .setOtherBirthCountry(regionService.findBysCode(address.getOtherBirthCountry()))
                    .setOtherCountry(regionService.findBysCode(address.getOtherCountry()));
            accountPerAddressModels.add(accountPerAddressModel);
        }
        return accountPerAddressModels;
    }
}
