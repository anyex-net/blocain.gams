/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountPerInvestAccount;

/**
 * 个人一体化投资管理账户功能 持久层接口
 * <p>File：AccountPerInvestAccountMapper.java </p>
 * <p>Title: AccountPerInvestAccountMapper </p>
 * <p>Description:AccountPerInvestAccountMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountPerInvestAccountMapper extends GenericMapper<AccountPerInvestAccount>
{

}
