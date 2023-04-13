/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoAsset;
import com.fintech.gams.exception.BusinessException;


/**
 * 公司资产信息 服务接口
 * <p>File：AccountCoAssetService.java </p>
 * <p>Title: AccountCoAssetService </p>
 * <p>Description:AccountCoAssetService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoAssetService extends GenericService<AccountCoAsset> {


    /**
     *  根据  accountId 查询公司资产信息
     * @param accountId
     * @return
     * @throws BusinessException
     */
    AccountCoAsset findByAccountId(Long accountId) throws BusinessException;
}
