/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoAccountPortfolio;
import com.fintech.gams.exception.BusinessException;

import javax.validation.constraints.NotNull;

/**
 * 公司账户组合信息 服务接口
 * <p>File：AccountCoAccountPortfolioService.java </p>
 * <p>Title: AccountCoAccountPortfolioService </p>
 * <p>Description:AccountCoAccountPortfolioService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoAccountPortfolioService extends GenericService<AccountCoAccountPortfolio> {


    /**
     * 根据AccountId取公司账户组合信息
     *
     * @param accountId
     * @return
     * @throws BusinessException
     */
    AccountCoAccountPortfolio findByAccountId(Long accountId) throws BusinessException;
}
