/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParAddressModel;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountParAddress;
import com.fintech.gams.account.mapper.AccountParAddressMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 合伙企业地址信息 服务实现类
 * <p>File：AccountParAddressServiceImpl.java </p>
 * <p>Title: AccountParAddressServiceImpl </p>
 * <p>Description:AccountParAddressServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountParAddressService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountParAddressServiceImpl extends GenericServiceImpl<AccountParAddress> implements AccountParAddressService
{

    protected AccountParAddressMapper accountParAddressMapper;
    @Autowired(required = false)
    private RegionService regionService;

    @Autowired(required = false)
    public AccountParAddressServiceImpl(AccountParAddressMapper accountParAddressMapper)
    {
        super(accountParAddressMapper);
        this.accountParAddressMapper = accountParAddressMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByParPersonalId(Long parPersonalId) throws BusinessException {
        return accountParAddressMapper.removeByParPersonalId(parPersonalId);
    }

    @Override
    public List<AccountParAddressModel> findList(AccountParAddress accountParAddress, String lang) {
        List<AccountParAddress> accountParAddresses = accountParAddressMapper.findList(accountParAddress);
        List<AccountParAddressModel> accountParAddressModels = new ArrayList<>();
        for(AccountParAddress address:accountParAddresses){
            AccountParAddressModel accountParAddressModel = new AccountParAddressModel()
                    .convertToModel(address)
                    .setCountry(regionService.findBysCode(address.getCountry()))
                    .setOtherBirthCountry(regionService.findBysCode(address.getOtherBirthCountry()))
                    .setOtherCountry(regionService.findBysCode(address.getOtherCountry()));
            accountParAddressModels.add(accountParAddressModel);
        }
        return accountParAddressModels;
    }
}
