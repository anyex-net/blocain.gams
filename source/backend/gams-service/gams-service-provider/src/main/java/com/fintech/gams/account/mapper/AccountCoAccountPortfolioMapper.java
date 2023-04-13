/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoAccountPortfolio;

/**
 * 公司账户组合信息 持久层接口
 * <p>File：AccountCoAccountPortfolioMapper.java </p>
 * <p>Title: AccountCoAccountPortfolioMapper </p>
 * <p>Description:AccountCoAccountPortfolioMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoAccountPortfolioMapper extends GenericMapper<AccountCoAccountPortfolio> {
    /**
     * 根据AccountId取公司账户组合信息
     *
     * @param accountId
     * @return
     */
    AccountCoAccountPortfolio findByAccountId(Long accountId);
}
