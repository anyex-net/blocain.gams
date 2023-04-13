/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountPerIdentity;

/**
 * 个人身份信息 持久层接口
 * <p>File：AccountPerIdentityMapper.java </p>
 * <p>Title: AccountPerIdentityMapper </p>
 * <p>Description:AccountPerIdentityMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountPerIdentityMapper extends GenericMapper<AccountPerIdentity>
{

}
