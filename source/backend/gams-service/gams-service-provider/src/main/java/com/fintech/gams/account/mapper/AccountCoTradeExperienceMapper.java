/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoTradeExperience;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 公司交易经验信息 持久层接口
 * <p>File：AccountCoTradeExperienceMapper.java </p>
 * <p>Title: AccountCoTradeExperienceMapper </p>
 * <p>Description:AccountCoTradeExperienceMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoTradeExperienceMapper extends GenericMapper<AccountCoTradeExperience>
{

}
