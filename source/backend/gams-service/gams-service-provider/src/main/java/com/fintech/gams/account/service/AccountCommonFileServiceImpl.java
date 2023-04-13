/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.AccountCommonFile;
import com.fintech.gams.account.mapper.AccountCommonFileMapper;

import java.util.List;

/**
 * 公共的文件 服务实现类
 * <p>File：AccountCommonFileServiceImpl.java </p>
 * <p>Title: AccountCommonFileServiceImpl </p>
 * <p>Description:AccountCommonFileServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = AccountCommonFileService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class AccountCommonFileServiceImpl extends GenericServiceImpl<AccountCommonFile> implements AccountCommonFileService
{

    protected AccountCommonFileMapper accountCommonFileMapper;

    @Autowired(required = false)
    public AccountCommonFileServiceImpl(AccountCommonFileMapper accountCommonFileMapper)
    {
        super(accountCommonFileMapper);
        this.accountCommonFileMapper = accountCommonFileMapper;
    }

    @Override
    public List<AccountCommonFile> findByKindArr(Long[] commonFileKindArr, String lang) throws BusinessException {
        return accountCommonFileMapper.findByKindArr(commonFileKindArr,lang);
    }
}
