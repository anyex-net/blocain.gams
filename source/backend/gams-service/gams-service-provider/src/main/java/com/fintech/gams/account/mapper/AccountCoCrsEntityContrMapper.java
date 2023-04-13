/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoCrsEntityContr;


/**
 * 公司crs实体（CRS-E）控权人信息 持久层接口
 * <p>File：AccountCoCrsEntityContrMapper.java </p>
 * <p>Title: AccountCoCrsEntityContrMapper </p>
 * <p>Description:AccountCoCrsEntityContrMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoCrsEntityContrMapper extends GenericMapper<AccountCoCrsEntityContr>
{


}
