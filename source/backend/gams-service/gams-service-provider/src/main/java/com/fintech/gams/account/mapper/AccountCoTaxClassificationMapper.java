/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoTaxClassification;

/**
 * 公司税收分类 持久层接口
 * <p>File：AccountCoTaxClassificationMapper.java </p>
 * <p>Title: AccountCoTaxClassificationMapper </p>
 * <p>Description:AccountCoTaxClassificationMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoTaxClassificationMapper extends GenericMapper<AccountCoTaxClassification>
{
    /**
     *  根据AccountId取公司税收分类
     * @param accountId
     * @return
     */
    AccountCoTaxClassification findByAccountId(Long accountId);
}
