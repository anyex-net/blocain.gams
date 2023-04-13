/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoAsset;

/**
 * 公司资产信息 持久层接口
 * <p>File：AccountCoAssetMapper.java </p>
 * <p>Title: AccountCoAssetMapper </p>
 * <p>Description:AccountCoAssetMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoAssetMapper extends GenericMapper<AccountCoAsset>
{
    /**
     *  根据  accountId 查询公司资产信息
     * @param accountId
     * @return
     */
    AccountCoAsset findByAccountId(Long accountId);
}
