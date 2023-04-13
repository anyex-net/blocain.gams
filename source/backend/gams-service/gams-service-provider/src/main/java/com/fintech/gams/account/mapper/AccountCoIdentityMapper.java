/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoIdentity;

/**
 * 公司身份信息 持久层接口
 * <p>File：AccountCoIdentityMapper.java </p>
 * <p>Title: AccountCoIdentityMapper </p>
 * <p>Description:AccountCoIdentityMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoIdentityMapper extends GenericMapper<AccountCoIdentity> {
    /**
     * 根据 AccountId 查询 公司身份信息
     *
     * @param accountId
     * @return
     */
    AccountCoIdentity findByAccountId(Long accountId);

    /**
     * 删除公司身份信息  通过 coPersonalId
     * @param coPersonalId
     * @return
     */
    int removeByCoPersonalId(Long coPersonalId);
}
