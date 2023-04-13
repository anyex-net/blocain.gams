/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountCoRegulatory;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoTaxClassification;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 公司税收分类 服务接口
 * <p>File：AccountCoTaxClassificationService.java </p>
 * <p>Title: AccountCoTaxClassificationService </p>
 * <p>Description:AccountCoTaxClassificationService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoTaxClassificationService extends GenericService<AccountCoTaxClassification> {


    /**
     * 根据AccountId取公司税收分类
     *
     * @param accountId
     * @return
     * @throws BusinessException
     */
    AccountCoTaxClassification findByAccountId(Long accountId) throws BusinessException;


}
