/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountParInvestmentTarget;

/**
 * 合伙企业投资目标信息 持久层接口
 * <p>File：AccountParInvestmentTargetMapper.java </p>
 * <p>Title: AccountParInvestmentTargetMapper </p>
 * <p>Description:AccountParInvestmentTargetMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountParInvestmentTargetMapper extends GenericMapper<AccountParInvestmentTarget>
{

}
