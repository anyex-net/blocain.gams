/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountCoAddressModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.SerialnoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.gams.account.entity.AccountCoAddress;
import com.fintech.gams.account.mapper.AccountCoAddressMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司地址信息 服务实现类
 * <p>File：AccountCoAddressServiceImpl.java </p>
 * <p>Title: AccountCoAddressServiceImpl </p>
 * <p>Description:AccountCoAddressServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountCoAddressServiceImpl extends GenericServiceImpl<AccountCoAddress> implements AccountCoAddressService
{

    protected AccountCoAddressMapper accountCoAddressMapper;
    @Autowired(required = false)
    private RegionService regionService;

    @Autowired(required = false)
    public AccountCoAddressServiceImpl(AccountCoAddressMapper accountCoAddressMapper)
    {
        super(accountCoAddressMapper);
        this.accountCoAddressMapper = accountCoAddressMapper;
    }


    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeByCoPersonalId(Long coPersonalId) throws BusinessException {
        return accountCoAddressMapper.removeByCoPersonalId(coPersonalId);
    }

    @Override
    public List<AccountCoAddressModel> findList(AccountCoAddress accountCoAddress, String lang) {
        List<AccountCoAddress> accountCoAddresses = accountCoAddressMapper.findList(accountCoAddress);
        List<AccountCoAddressModel> accountCoAddressModels = new ArrayList<>();
        for(AccountCoAddress address:accountCoAddresses){
            AccountCoAddressModel accountCoAddressModel = new AccountCoAddressModel()
                    .convertToModel(address)
                    .setCountry(regionService.findBysCode(address.getCountry()))
                    .setOtherBirthCountry(regionService.findBysCode(address.getOtherBirthCountry()))
                    .setOtherCountry(regionService.findBysCode(address.getOtherCountry()));
            accountCoAddressModels.add(accountCoAddressModel);
        }
        return accountCoAddressModels;
    }
}
