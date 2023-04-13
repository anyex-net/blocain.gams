/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoInstitutional;
import com.fintech.gams.exception.BusinessException;

import javax.validation.constraints.NotNull;

/**
 * 公司机构 服务接口
 * <p>File：AccountCoInstitutionalService.java </p>
 * <p>Title: AccountCoInstitutionalService </p>
 * <p>Description:AccountCoInstitutionalService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoInstitutionalService extends GenericService<AccountCoInstitutional> {

    /**
     * 根据AccountId取公司机构信息
     *
     * @param accountId
     * @return
     * @throws BusinessException
     */
    AccountCoInstitutional findByAccountId(Long accountId) throws BusinessException;
}
