/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoInstitutional;

/**
 * 公司机构 持久层接口
 * <p>File：AccountCoInstitutionalMapper.java </p>
 * <p>Title: AccountCoInstitutionalMapper </p>
 * <p>Description:AccountCoInstitutionalMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoInstitutionalMapper extends GenericMapper<AccountCoInstitutional>
{
    /**
     *  根据AccountId取公司机构信息
     * @param accountId
     * @return
     */
    AccountCoInstitutional findByAccountId(Long accountId);
}
