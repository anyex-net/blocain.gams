/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountParContact;

/**
 * 合伙企业联系信息 持久层接口
 * <p>File：AccountParContactMapper.java </p>
 * <p>Title: AccountParContactMapper </p>
 * <p>Description:AccountParContactMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountParContactMapper extends GenericMapper<AccountParContact>
{
    /**
     * 删除合伙企业联系信息  通过 parPersonalId
     *
     * @param parPersonalId
     * @return
     */
    int removeByParPersonalId(Long parPersonalId);
}
