/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.account.entity.AccountLog;
import com.fintech.gams.bean.GenericMapper;

/**
 * 帐户日志 持久层接口
 * <p>File：AccountLogMapper.java </p>
 * <p>Title: AccountLogMapper </p>
 * <p>Description:AccountLogMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountLogMapper extends GenericMapper<AccountLog>
{

}
