/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoIdentity;
import com.fintech.gams.exception.BusinessException;

import javax.validation.constraints.NotNull;

/**
 * 公司身份信息 服务接口
 * <p>File：AccountCoIdentityService.java </p>
 * <p>Title: AccountCoIdentityService </p>
 * <p>Description:AccountCoIdentityService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoIdentityService extends GenericService<AccountCoIdentity> {


    /**
     * 根据 AccountId 查询 公司身份信息
     *
     * @param accountId
     * @return
     * @throws BusinessException
     */
    AccountCoIdentity findByAccountId(Long accountId) throws BusinessException;

    /**
     * 删除公司身份信息  通过 coPersonalId
     *
     * @param coPersonalId
     * @return
     * @throws BusinessException
     */
    int removeByCoPersonalId(Long coPersonalId) throws BusinessException;
}
