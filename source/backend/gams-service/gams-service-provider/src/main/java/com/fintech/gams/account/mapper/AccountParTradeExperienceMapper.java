/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountParTradeExperience;

/**
 * 合伙企业交易经验信息 持久层接口
 * <p>File：AccountParTradeExperienceMapper.java </p>
 * <p>Title: AccountParTradeExperienceMapper </p>
 * <p>Description:AccountParTradeExperienceMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountParTradeExperienceMapper extends GenericMapper<AccountParTradeExperience>
{

}
