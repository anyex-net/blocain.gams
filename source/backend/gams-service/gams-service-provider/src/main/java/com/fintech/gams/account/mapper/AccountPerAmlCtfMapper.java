/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountPerAmlCtf;

/**
 * 个人AML/CTF风险问卷 持久层接口
 * <p>File：AccountPerAmlCtfMapper.java </p>
 * <p>Title: AccountPerAmlCtfMapper </p>
 * <p>Description:AccountPerAmlCtfMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountPerAmlCtfMapper extends GenericMapper<AccountPerAmlCtf>
{

}
