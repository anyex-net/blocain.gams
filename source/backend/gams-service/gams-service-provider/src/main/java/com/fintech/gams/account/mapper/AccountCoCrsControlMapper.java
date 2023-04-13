/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoCrsControl;

/**
 * 公司CRS控权人自我证明表格 持久层接口
 * <p>File：AccountCoCrsControlMapper.java </p>
 * <p>Title: AccountCoCrsControlMapper </p>
 * <p>Description:AccountCoCrsControlMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoCrsControlMapper extends GenericMapper<AccountCoCrsControl>
{
    /**
     *   通过AccountId 查询公司CRS控权人自我证明表格（多表查询）
     * @param accountId
     * @return
     */
    AccountCoCrsControl findByAccountId(Long accountId);

    /**
     * 删除CRS控权人自我证明表格 通过 coCrsEntityContrId
     * @param coCrsEntityContrId
     * @return
     */
    int removeByCoCrsEntityContrId(Long coCrsEntityContrId);
}
