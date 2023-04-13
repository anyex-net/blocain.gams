/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoInvestmentTarget;

/**
 * 公司投资目标信息 持久层接口
 * <p>File：AccountCoInvestmentTargetMapper.java </p>
 * <p>Title: AccountCoInvestmentTargetMapper </p>
 * <p>Description:AccountCoInvestmentTargetMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoInvestmentTargetMapper extends GenericMapper<AccountCoInvestmentTarget>
{
    /**
     * 根据AccountId取公司投资目标信息
     * @param accountId
     * @return
     */
    AccountCoInvestmentTarget findByAccountId(Long accountId);
}