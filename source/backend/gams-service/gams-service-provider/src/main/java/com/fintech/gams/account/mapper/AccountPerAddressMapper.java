/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import com.fintech.gams.account.entity.AccountPerAddress;
import com.fintech.gams.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 个人地址 持久层接口
 * <p>File：AccountPerAddressMapper.java </p>
 * <p>Title: AccountPerAddressMapper </p>
 * <p>Description:AccountPerAddressMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountPerAddressMapper extends GenericMapper<AccountPerAddress>
{

}
