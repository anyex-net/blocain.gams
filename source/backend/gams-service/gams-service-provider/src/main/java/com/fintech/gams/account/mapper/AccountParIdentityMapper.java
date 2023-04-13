/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountParIdentity;

/**
 * 合伙企业身份信息 持久层接口
 * <p>File：AccountParIdentityMapper.java </p>
 * <p>Title: AccountParIdentityMapper </p>
 * <p>Description:AccountParIdentityMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountParIdentityMapper extends GenericMapper<AccountParIdentity>
{

    /**
     * 删除合伙企业身份信息  通过 parPersonalId
     *
     * @param parPersonalId
     * @return
     */
    int removeByParPersonalId(Long parPersonalId);
}
