/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoAmlCtf;

/**
 * 公司AML/CTF风险问卷 持久层接口
 * <p>File：AccountCoAmlCtfMapper.java </p>
 * <p>Title: AccountCoAmlCtfMapper </p>
 * <p>Description:AccountCoAmlCtfMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoAmlCtfMapper extends GenericMapper<AccountCoAmlCtf>
{

}
