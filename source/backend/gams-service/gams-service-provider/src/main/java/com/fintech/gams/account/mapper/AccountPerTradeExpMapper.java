/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import com.fintech.gams.account.entity.AccountPerTradeExp;
import com.fintech.gams.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 个人交易经验信息 持久层接口
 * <p>File：AccountPerTradeExpMapper.java </p>
 * <p>Title: AccountPerTradeExpMapper </p>
 * <p>Description:AccountPerTradeExpMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountPerTradeExpMapper extends GenericMapper<AccountPerTradeExp>
{

}
