/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoPersonal;
import org.apache.ibatis.annotations.Param;

/**
 * 公司个人信息 持久层接口
 * <p>File：AccountCoPersonalMapper.java </p>
 * <p>Title: AccountCoPersonalMapper </p>
 * <p>Description:AccountCoPersonalMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoPersonalMapper extends GenericMapper<AccountCoPersonal> {

}
